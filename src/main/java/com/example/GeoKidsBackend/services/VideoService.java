package com.example.GeoKidsBackend.services;

import java.io.IOException;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.GeoKidsBackend.model.User;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.model.Upload.Video;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.example.GeoKidsBackend.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class VideoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;
    
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UserRepository userRepository;
    public String addVideo(Destination destination, String userID, MultipartFile file) throws IOException { 
        DBObject metaData = new BasicDBObject(); 
        metaData.put("type", "video"); 
        ObjectId id = gridFsTemplate.store(
          file.getInputStream(), file.getName(), file.getContentType(), metaData);
        
        Upload upload = uploadRepository.findByUserIdAndDestination_placeID(userID, destination.getPlaceID());
        
        if(upload == null) {
        	User user = userRepository.findById(userID).orElseThrow();
            Upload newUpload = new Upload(destination, user);
            newUpload.getVideos().add(id.toString());
            uploadRepository.save(newUpload);
            return id.toString();
        }
        upload.getVideos().add(id.toString());
        return id.toString();
    }

    public Video getVideo(String id) throws IllegalStateException, IOException { 
    	GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        Video video = new Video(); 
        video.setStream(operations.getResource(file).getInputStream());
        return video; 
    }
}
