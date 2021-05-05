package com.example.GeoKidsBackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeoKidsBackend.model.Classification;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.payload.ClassificationAggregation;
import com.example.GeoKidsBackend.payload.GetAllUploadsPayload;
import com.example.GeoKidsBackend.payload.UpdateUploadActivePayload;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/upload")
public class AdminUploadController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;
    
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    @Autowired
    UploadRepository uploadRep;
    
	@DeleteMapping("/deleteVideoOrAudio")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteVideoOrAudio(@RequestParam String id){
		Query removeQuery = Query.query(Criteria.where("uploads").elemMatch(Criteria.where("data").is(id)));
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
		Update update = 
				   new Update().pull("uploads", 
				       new BasicDBObject("data", id));

		mongoTemplate.updateMulti(removeQuery, update, Upload.class);
		return "Yep";
	}
	

	@DeleteMapping("/deleteTextOrImage")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteTextOrImage(@RequestParam String id){
		Query removeQuery = Query.query(Criteria.where("uploads").elemMatch(Criteria.where("uploadID").is(id)));
		Update update = 
				   new Update().pull("uploads", 
				       new BasicDBObject("uploadID", id));
		mongoTemplate.updateMulti(removeQuery, update, Upload.class);
		return "Yep";
	}
	
	/*@PutMapping("/updateIsActive")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateActive(@Valid @RequestBody UpdateUploadActivePayload payload){
		Query query = Query.query(Criteria.where("uploads").elemMatch(Criteria.where("uploadID").is(payload.getId())));
		Update update = new Update().set("uploads.$.isActive", payload.getActive());

		UpdateResult asdf = mongoTemplate.updateMulti(query, update, Upload.class);
		System.out.println(asdf);
		return "Yep";
	}*/
	@PutMapping("/updateIsActive")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateActive(@RequestParam String id, @RequestParam boolean active){
		Query query = Query.query(Criteria.where("uploads").elemMatch(Criteria.where("uploadID").is(id)));
		Update update = new Update().set("uploads.$.isActive",active);

		UpdateResult asdf = mongoTemplate.updateMulti(query, update, Upload.class);
		System.out.println(asdf);
		return "Yep";
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getAllUploads(@RequestParam String city){
		/*GroupOperation groupOperation = Aggregation.group("destination", "uploads");
		ProjectionOperation projectionOperation = Aggregation.project("uploads").and("destination").previousOperation();
		Aggregation aggregation1 = Aggregation.newAggregation(groupOperation, projectionOperation);*/
		
		/*TypedAggregation<Upload> uploadAggregation = Aggregation.newAggregation(Upload.class,
	              Aggregation.group("destination").
	                 push(new BasicDBObject
	                       ("uploads", "$uploads")).as("uploads"));
		AggregationResults<Upload> result = mongoTemplate.aggregate(uploadAggregation, "upload", Upload.class);
		return result.getMappedResults();*/
		UnwindOperation unwindOperation = Aggregation.unwind("uploads");
		GroupOperation groupOperation = Aggregation.group("destination").addToSet("uploads").as("uploads");
		ProjectionOperation projectionOperation = Aggregation.project("uploads").and("destination").previousOperation();
		Aggregation uploadAggregation = Aggregation.newAggregation(Upload.class, unwindOperation,groupOperation, projectionOperation);
		
		//Aggregation uploadAggregation = Aggregation.newAggregation(Upload.class, Aggregation.group("destination").addToSet("uploads").as("uploads"));
		AggregationResults<GetAllUploadsPayload> result = mongoTemplate.aggregate(uploadAggregation, "upload", GetAllUploadsPayload.class);
		return result.getMappedResults();
	}
    
}
