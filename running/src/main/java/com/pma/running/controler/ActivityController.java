package com.pma.running.controler;

import com.pma.running.dto.ActivityDto;
import com.pma.running.dto.ActivityResponseDto;
import com.pma.running.model.Activity;
import com.pma.running.model.Post;
import com.pma.running.service.ActivityService;
import com.pma.running.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "", allowedHeaders = "", maxAge = 3600)
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {

    private final ActivityService activityService;
    private final PostService postService;

    @Autowired
    public ActivityController(ActivityService activityService, PostService postService) {
        this.activityService = activityService;
        this.postService = postService;
    }

    @PostMapping("/activities/share")
    public ResponseEntity<Post> shareActivity(@RequestBody ActivityDto activityDto) {
        Activity activity = activityService.save(activityDto);
        return new ResponseEntity<>(postService.save(activityDto, activity.getId()), HttpStatus.OK);
    }

    @GetMapping("/getActivitiesByUser/{userId}")
    public ResponseEntity<Set<ActivityResponseDto>> getActivitiesByUser(@PathVariable Long userId) {
        Set<Activity> activity = activityService.findActivityByUserId(userId);
        Set<ActivityResponseDto> response = new HashSet<>();
        if (activity == null) {
            return new ResponseEntity<Set<ActivityResponseDto>>(response, HttpStatus.OK);
        }
        for (Activity act: activity) {
            ActivityResponseDto responseO = new ActivityResponseDto();
            responseO.setDistance(act.getDistance());
            responseO.setDuration(act.getDuration());
            responseO.setEncodedMap(act.getEncodedMap());
            responseO.setDateTime(act.getDateTime().toString());
            responseO.setId(act.getId());
            response.add(responseO);
        }

        return new ResponseEntity<Set<ActivityResponseDto>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{userId}/{activityId}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable Long userId, @PathVariable Long activityId) {
        Activity deletedActivity = postService.delete(userId, activityId);
        if (deletedActivity != null) {
            return new ResponseEntity<Activity>(deletedActivity, HttpStatus.OK);
        }
        return new ResponseEntity<Activity>(deletedActivity, HttpStatus.NOT_FOUND);
    }

}