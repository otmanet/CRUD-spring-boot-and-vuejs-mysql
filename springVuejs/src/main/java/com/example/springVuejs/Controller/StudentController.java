package com.example.springVuejs.Controller;

import java.util.List;

import com.example.springVuejs.Service.serviceStudent;
import com.example.springVuejs.model.Student;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private serviceStudent servicestudent;

    @RequestMapping(name = "/")
    public JSONObject listStudent(Model model) {
        List<Student> students = servicestudent.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("message", "Data is found");
        jsonObject.put("students", students);
        return jsonObject;
    }

    @RequestMapping("/save")
    public JSONObject saveStudent(@RequestBody Student student) {
        servicestudent.save(student);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("etat", true);
        return jsonObject;
    }

    @GetMapping(path = "/deleteStudent/{id}")
    public JSONObject deleteStudent(@PathVariable("id") long id) {
        servicestudent.delete(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("etat", true);
        return jsonObject;
    }

    @PostMapping("/updateStudent")
    public JSONObject edit(@RequestBody Student student) {
        servicestudent.save(student);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("etat", true);
        return jsonObject;
    }
}
