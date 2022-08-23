package org.saeta.controller;

import org.saeta.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("files")
public class FileController {

    @Autowired
    FileService fileService;

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
        HashMap<String, Object> responseMap = new HashMap<>();
        try{
            fileService.deleteFile(file.getOriginalFilename());
        }catch (IOException e){
            System.out.println("No existe el archivo"   );
        }
        fileService.save(file);
        responseMap.put("status", "ok");
        responseMap.put("fileName", file.getOriginalFilename());
        return new ResponseEntity(responseMap, HttpStatus.OK);

    }
}
