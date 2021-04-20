package com.example.GeoKidsBackend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.example.GeoKidsBackend.payload.PostUploadResponse;
import com.example.GeoKidsBackend.payload.UploadPayloadResponse2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.Audio;
import com.example.GeoKidsBackend.model.Upload.Image;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.model.Upload.Video;
import com.example.GeoKidsBackend.payload.PostClassificationRequest;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.example.GeoKidsBackend.services.AudioService;
import com.example.GeoKidsBackend.services.ImageService;
import com.example.GeoKidsBackend.services.TextService;
import com.example.GeoKidsBackend.services.VideoService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

	@Autowired
	ImageService imageService;
	
	@Autowired
	VideoService videoService;
	
	@Autowired
	AudioService audioService;
	
	@Autowired
	TextService textService;
	
	@Autowired
	UploadRepository uploadRepository;
	
	@PostMapping("/image/add")
	public UploadPayloadResponse2 addPhoto(@RequestParam("designation") String designation, @RequestParam("placeID") String placeID,
			@RequestParam("userID") String userID,@RequestParam("image") MultipartFile image, @RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws IOException{
				Destination destination = new Destination(designation, latitude, longitude, placeID);
				Image newImage = imageService.addImage(destination, userID, image);
				String base64 = "data:image/png;base64,";
				String imageString = base64 + Base64.getEncoder().encodeToString(newImage.getImage().getData());
				UploadPayloadResponse2 u = new UploadPayloadResponse2("image", imageString);
				return u;
				/*PostUploadResponse postUploadRes = new PostUploadResponse(destination);
				ArrayList<String> images = new ArrayList<>();
				String base64 = "data:image/png;base64,";
				for(Image i : upload.getImages()) {
					String imageString = base64 + Base64.getEncoder().encodeToString(i.getImage().getData());
					images.add(imageString);
				}
				postUploadRes.setImages(images);
				postUploadRes.setVideos(upload.getVideos());
				return postUploadRes;*/
	}
	
	@PostMapping("/image/getAll")
	public String getImages(@Valid @RequestBody PostClassificationRequest postClassificationRequest){
		System.out.println("fui chamado");
		Upload upload = uploadRepository.findByUserIdAndDestination_placeID(postClassificationRequest.getUserID(), postClassificationRequest.getDestination().getPlaceID());
		Image image = upload.getImages().get(0);
		String imageString = Base64.getEncoder().encodeToString(image.getImage().getData());
		//byte data[] = image.getImage().getData();
		//return data;
		return imageString;
		
	}
	
	@PostMapping("/video/add")
	public UploadPayloadResponse2 addVideo(@RequestParam("designation") String designation, @RequestParam("placeID") String placeID,
			@RequestParam("userID") String userID,@RequestParam("video") MultipartFile file, @RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws IOException {
		
		Destination destination = new Destination(designation, latitude, longitude, placeID);
	    String id = videoService.addVideo(destination, userID, file);
	    
		UploadPayloadResponse2 u = new UploadPayloadResponse2("video", id);
		return u;
	    /*PostUploadResponse postUploadRes = new PostUploadResponse(destination);
		ArrayList<String> images = new ArrayList<>();
		String base64 = "data:image/png;base64,";
		for(Image i : upload.getImages()) {
			String imageString = base64 + Base64.getEncoder().encodeToString(i.getImage().getData());
			images.add(imageString);
		}
		postUploadRes.setImages(images);
		postUploadRes.setVideos(upload.getVideos());
	    return postUploadRes;*/
	}
	
	@GetMapping("/video/stream/{id}")
	public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
	    Video video = videoService.getVideo(id);
	    FileCopyUtils.copy(video.getStream(), response.getOutputStream());
	}
	
	@PostMapping("/audio/add")
	public UploadPayloadResponse2 addAudio(@RequestParam("designation") String designation, @RequestParam("placeID") String placeID,
			@RequestParam("userID") String userID,@RequestParam("audio") MultipartFile file, @RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws IOException {
		
		Destination destination = new Destination(designation, latitude, longitude, placeID);
	    String id = audioService.addAudio(destination, userID, file);
	    
		UploadPayloadResponse2 u = new UploadPayloadResponse2("audio", id);
		return u;
	    /*PostUploadResponse postUploadRes = new PostUploadResponse(destination);
		ArrayList<String> images = new ArrayList<>();
		String base64 = "data:image/png;base64,";
		for(Image i : upload.getImages()) {
			String imageString = base64 + Base64.getEncoder().encodeToString(i.getImage().getData());
			images.add(imageString);
		}
		postUploadRes.setImages(images);
		postUploadRes.setVideos(upload.getVideos());
	    return postUploadRes;*/
	}
	
	@GetMapping("/audio/stream/{id}")
	public void streamAudio(@PathVariable String id, HttpServletResponse response) throws Exception {
	    Audio audio = audioService.getAudio(id);
	    FileCopyUtils.copy(audio.getStream(), response.getOutputStream());
	}
	
	@PostMapping("/text/add")
	public UploadPayloadResponse2 addText(@RequestParam("designation") String designation, @RequestParam("placeID") String placeID,
			@RequestParam("userID") String userID,@RequestParam("text") String text, @RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws IOException {
		
		Destination destination = new Destination(designation, latitude, longitude, placeID);
	    String newT = textService.addText(destination, userID, text);
	    
		UploadPayloadResponse2 u = new UploadPayloadResponse2("text", newT);
		return u;

	}

}
