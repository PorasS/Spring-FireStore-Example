package com.pratech.SpringFireStoreservice;

import com.pratech.SpringFireStoreservice.model.Collection;
import com.pratech.SpringFireStoreservice.model.Student;
import com.pratech.SpringFireStoreservice.model.Users;
import com.pratech.SpringFireStoreservice.service.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("firestore")
public class FireStoreController {

    @Autowired
    FireBaseService fireBaseService;

    @RequestMapping("/save")
    public String iniializeFirestore() throws IOException, ExecutionException, InterruptedException {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        Users user = new Users();
        user.setCreatedAt(ts);
        user.setDisplayName("Thor");
        user.setEmail("asgardian@gmail.com");
        return fireBaseService.saveUserDetails(user);
    }

    @RequestMapping("/getstud")
    public Student getStud() throws ExecutionException, InterruptedException {
        return fireBaseService.getStudentDetails();
    }

    @RequestMapping("/student")
    public List<Student> getStudentCollection() throws ExecutionException, InterruptedException {
        return fireBaseService.getStudentCollection();
    }

    @RequestMapping("/getcollection")
    public List<Collection> getCollection() throws ExecutionException, InterruptedException {
        return fireBaseService.getCollections();
    }
}
