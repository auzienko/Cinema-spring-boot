package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageType;
import edu.school21.cinema.services.PosterService;
import javafx.geometry.Pos;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/images")
public class Images {
    private Environment env;
    private PosterService posterService;

    @Autowired
    public Images(Environment env, PosterService posterService) {
        this.env = env;
        this.posterService = posterService;
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

    @GetMapping("/avatar/{id}")
    @ResponseBody
    public void getAvatarPage(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Image> avatar = posterService.findByUUID(UUID.fromString(id));
        if (avatar.isPresent() && avatar.get().getType() == ImageType.AVATAR) {
            String relativeImagePath = env.getProperty("storage.path") + "/" + id;
            resp.setContentType(avatar.get().getMime());
            ServletOutputStream outStream;
            outStream = resp.getOutputStream();
            FileInputStream fin = new FileInputStream(relativeImagePath);
            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(outStream);
            int ch = 0;
            while ((ch = bin.read()) != -1)
                bout.write(ch);
            bin.close();
            fin.close();
            bout.close();
            outStream.close();
        }
    }
}
