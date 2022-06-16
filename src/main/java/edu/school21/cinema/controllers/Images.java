package edu.school21.cinema.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/images")
public class Images {
    private Environment env;

    @Autowired
    public Images(Environment env) {
        this.env = env;
    }

    @GetMapping("{id}")
    @ResponseBody
    public byte[] getPage(@PathVariable String id) {
        try {
            return FileUtils.readFileToByteArray(new File(env.getProperty("storage.path") + "/" + id));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
