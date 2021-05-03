package com.example.GeoKidsBackend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation.GroupOperationBuilder;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.payload.ClassificationAggregation;
import com.example.GeoKidsBackend.payload.ClassificationFavouriteAgg;
import com.example.GeoKidsBackend.payload.ClassificationTotalRatingsAgg;
import com.example.GeoKidsBackend.model.Classification;
import com.example.GeoKidsBackend.repository.ClassificationRepository;
import com.mongodb.BasicDBObject;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/ratings")
public class AdminRatingsController {

	@Autowired
	ClassificationRepository classificationRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@GetMapping("/getClassifications")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getClassifications(@RequestParam String city){
		List<Classification> ratings = classificationRepo.findAll();
		return ratings;
	}
	@GetMapping("/getTest")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getTest(@RequestParam String city){
		GroupOperation groupOperation = Aggregation.group("destination").avg("stars").as("avgRating");
		ProjectionOperation projectionOperation = Aggregation.project("avgRating").and("destination").previousOperation();
		MatchOperation matchOperation = Aggregation.match(new Criteria("avgRating").gt(0));
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "avgRating"));
		//LimitOperation limitToFirstFour = Aggregation.limit(4);
		Aggregation aggregation1 = Aggregation.newAggregation(groupOperation,projectionOperation,matchOperation, sortOperation);
		AggregationResults<ClassificationAggregation> result1 = mongoTemplate.aggregate(aggregation1, "classification", ClassificationAggregation.class);
		 List<ClassificationAggregation> avgRatingAgg = result1.getMappedResults();
		
		MatchOperation matchOperation2 = Aggregation.match(new Criteria("isFavorite").exists(true));
		GroupOperation groupOperation2 = Aggregation.group("destination").count().as("favouriteCount");
	    ProjectionOperation projectionOperation2 = Aggregation.project("favouriteCount").and("destination").previousOperation();
        SortOperation sortOperation2 = Aggregation.sort(Sort.by(Sort.Direction.DESC, "favouriteCount"));
        //LimitOperation limitToFirstFour2 = Aggregation.limit(4);
        Aggregation aggregation2 = Aggregation.newAggregation(matchOperation2, groupOperation2,projectionOperation2, sortOperation2);
        AggregationResults<ClassificationAggregation> result2 = mongoTemplate.aggregate(aggregation2, "classification", ClassificationAggregation.class);
        List<ClassificationAggregation> favouriteAgg = result2.getMappedResults();
        
		GroupOperation groupOperation3 = Aggregation.group("destination").count().as("totalRatings");
		ProjectionOperation projectionOperation3 = Aggregation.project("totalRatings").and("destination").previousOperation();
		MatchOperation matchOperation3 = Aggregation.match(new Criteria("totalRatings").gt(0));
		SortOperation sortOperation3 = Aggregation.sort(Sort.by(Sort.Direction.DESC, "totalRatings"));
		//LimitOperation limitToFirstFour3 = Aggregation.limit(4);
		Aggregation aggregation3 = Aggregation.newAggregation(groupOperation3,projectionOperation3,matchOperation3, sortOperation3);
		 AggregationResults<ClassificationAggregation> result3 = mongoTemplate.aggregate(aggregation3, "classification", ClassificationAggregation.class);
		 List<ClassificationAggregation> totalRatingsAgg = result3.getMappedResults();
		//ProjectionOperation projectionOperation = Aggregation.project("totalRatings", "avgRating").and("destination").previousOperation();
		//GroupOperation groupOperation2 = Aggregation.group("destination").count().as("totalRatings");
		//GroupOperation countIsFavourite = Aggregation.group("destination").sum("isFavorite").as("isFavouriteCount");
	    /*ProjectionOperation projectionOperation = Aggregation.project("destination")
	    		.andExpression("avgRating").as("avgRating")                
	    		.andExpression("totalRatings").as("totalRatings");*/
		//MatchOperation matchOperation = Aggregation.match(new Criteria("avgRating").gt(0));
        //SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "avgRating"));
        //LimitOperation limitToFirstFour = Aggregation.limit(4);
		 List<ClassificationAggregation> results = new ArrayList(favouriteAgg);
				
		 for(ClassificationAggregation c1 : results) {
			 for(ClassificationAggregation c2 : totalRatingsAgg) {
				 if(c1.getDestination().getPlaceID().equals(c2.getDestination().getPlaceID())) {
					 c1.setTotalRatings(c2.getTotalRatings());
				 }
			 }
		 }
		 for(ClassificationAggregation c1 : results) {
			 for(ClassificationAggregation c2 : avgRatingAgg) {
				 if(c1.getDestination().getPlaceID().equals(c2.getDestination().getPlaceID())) {
					 c1.setAvgRating(c2.getAvgRating());
				 }
			 }
		 }
		return results;
	}
	
	@GetMapping("/getTopFavourites")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getTopFavourites(@RequestParam String city){
		MatchOperation matchOperation2 = Aggregation.match(new Criteria("isFavorite").exists(true));
		GroupOperation groupOperation2 = Aggregation.group("destination").count().as("favouriteCount");
	    ProjectionOperation projectionOperation2 = Aggregation.project("favouriteCount").and("destination").previousOperation();
        SortOperation sortOperation2 = Aggregation.sort(Sort.by(Sort.Direction.DESC, "favouriteCount"));
        //LimitOperation limitToFirstFour2 = Aggregation.limit(4);
        Aggregation aggregation2 = Aggregation.newAggregation(matchOperation2, groupOperation2,projectionOperation2, sortOperation2);
        AggregationResults<ClassificationAggregation> result2 = mongoTemplate.aggregate(aggregation2, "classification", ClassificationAggregation.class);
        List<ClassificationAggregation> favouriteAgg = result2.getMappedResults();
        
        
        
        return favouriteAgg;
	}
	
	@GetMapping("/getTopRatings")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getTopRating(@RequestParam String city){
		GroupOperation groupOperation = Aggregation.group("destination").avg("stars").as("avgRating");
		ProjectionOperation projectionOperation = Aggregation.project("avgRating").and("destination").previousOperation();
		MatchOperation matchOperation = Aggregation.match(new Criteria("avgRating").gt(0));
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "avgRating"));
		//LimitOperation limitToFirstFour = Aggregation.limit(4);
		Aggregation aggregation1 = Aggregation.newAggregation(groupOperation,projectionOperation,matchOperation, sortOperation);
		AggregationResults<ClassificationAggregation> result1 = mongoTemplate.aggregate(aggregation1, "classification", ClassificationAggregation.class);
		 List<ClassificationAggregation> avgRatingAgg = result1.getMappedResults();
        
		GroupOperation groupOperation3 = Aggregation.group("destination").count().as("totalRatings");
		ProjectionOperation projectionOperation3 = Aggregation.project("totalRatings").and("destination").previousOperation();
		MatchOperation matchOperation3 = Aggregation.match(new Criteria("totalRatings").gt(0));
		SortOperation sortOperation3 = Aggregation.sort(Sort.by(Sort.Direction.DESC, "totalRatings"));
		//LimitOperation limitToFirstFour3 = Aggregation.limit(4);
		Aggregation aggregation3 = Aggregation.newAggregation(groupOperation3,projectionOperation3,matchOperation3, sortOperation3);
		AggregationResults<ClassificationAggregation> result3 = mongoTemplate.aggregate(aggregation3, "classification", ClassificationAggregation.class);
		List<ClassificationAggregation> totalRatingsAgg = result3.getMappedResults();
		 List<ClassificationAggregation> results = new ArrayList(avgRatingAgg);
			
		 for(ClassificationAggregation c1 : results) {
			 for(ClassificationAggregation c2 : totalRatingsAgg) {
				 if(c1.getDestination().getPlaceID().equals(c2.getDestination().getPlaceID())) {
					 c1.setTotalRatings(c2.getTotalRatings());
				 }
			 }
		 }
	    Comparator<ClassificationAggregation> comparator = Comparator.comparing(c -> c.getTotalRatings());
	    comparator = comparator.thenComparing(Comparator.comparing(c -> c.getAvgRating()));
	    Stream<ClassificationAggregation> streamC = results.stream().sorted(comparator);
	    List<ClassificationAggregation> sortedResults = streamC.collect(Collectors.toList());
	    Collections.reverse(sortedResults);
        return sortedResults;
	}
	@GetMapping("/getTest2")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getTest2(@RequestParam String placeID){
		MatchOperation matchOperation = Aggregation.match(new Criteria("isFavorite").exists(true));
		GroupOperation groupOperation = Aggregation.group("destination").count().as("favouriteCount");
	    ProjectionOperation projectionOperation = Aggregation.project("favouriteCount").and("destination").previousOperation();
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "favouriteCount"));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, projectionOperation, sortOperation);
        AggregationResults<ClassificationFavouriteAgg> result = mongoTemplate.aggregate(aggregation, "classification", ClassificationFavouriteAgg.class);

		return result.getMappedResults() ;
	}
	
	@GetMapping("/getTest3")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getTest3(@RequestParam String placeID){
		MatchOperation matchOperation = Aggregation.match(new Criteria("isFavorite").exists(true));
		GroupOperation groupOperation = Aggregation.group("destination").count().as("favouriteCount");
	    ProjectionOperation projectionOperation = Aggregation.project("favouriteCount").and("destination").previousOperation();
        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "favouriteCount"));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, projectionOperation, sortOperation);
        AggregationResults<ClassificationFavouriteAgg> result = mongoTemplate.aggregate(aggregation, "classification", ClassificationFavouriteAgg.class);

		return result.getMappedResults() ;
	}

}
