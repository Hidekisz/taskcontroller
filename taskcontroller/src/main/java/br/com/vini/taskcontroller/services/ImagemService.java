package br.com.vini.taskcontroller.services;

import br.com.vini.taskcontroller.entity.GymImageEntity;
import br.com.vini.taskcontroller.repositories.GymImageRepository;
import br.com.vini.taskcontroller.util.FUUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.bson.types.ObjectId;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;

@Service
public class ImagemService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GymImageRepository gymImageRepository;

    public String salvarImagem(String caminhoImagem,String nomeUsuario,LocalDate localDateTime) {
        try (InputStream inputStream = new FileInputStream(caminhoImagem)) {
            String data = FUUtil.montaData(localDateTime);
            String gridFsId = gridFsTemplate.store(inputStream, data, "image/png").toString();
            GymImageEntity gymImageEntity = new GymImageEntity(nomeUsuario,localDateTime,gridFsId);
            gymImageRepository.save(gymImageEntity);

            return gymImageEntity.getImageId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GridFsResource obterImagemPorData(LocalDate date) {
        try {
            GridFSFile gridFSFile = gridFsTemplate.findOne(new org.springframework.data.mongodb.core.query.Query(
                    org.springframework.data.mongodb.core.query.Criteria.where("date").is(date)));
            return gridFsTemplate.getResource(gridFSFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GridFsResource obterImagem(String gridFsId) {
        try {
            GridFSFile gridFSFile = gridFsTemplate.findOne(new org.springframework.data.mongodb.core.query.Query(
                    org.springframework.data.mongodb.core.query.Criteria.where("_id").is(gridFsId)));
            return gridFsTemplate.getResource(gridFSFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
