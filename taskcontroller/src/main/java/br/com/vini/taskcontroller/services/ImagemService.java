package br.com.vini.taskcontroller.services;

import br.com.vini.taskcontroller.dto.GerarPdfDTO;
import br.com.vini.taskcontroller.dto.GerarPdfDtoList;
import br.com.vini.taskcontroller.entity.GymImageEntity;
import br.com.vini.taskcontroller.repositories.GymImageRepository;
import br.com.vini.taskcontroller.util.FUUtil;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.data.mongodb.core.query.Query;


@Service
public class ImagemService {

    @Autowired
    private GymImageRepository gymImageRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public GerarPdfDtoList salvarImagem(String caminhoImagem, String nomeUsuario, LocalDate localDateTime) {
        try (InputStream inputStream = new FileInputStream(caminhoImagem)) {
            // Monta o nome do arquivo a ser salvo
            String data = FUUtil.montaData(localDateTime);

            // Armazena a imagem no GridFS e recupera o ID
            String gridFsId = gridFsTemplate.store(inputStream, data, "image/png").toString();

            // Reabrir o InputStream para a conversão para Base64
            try (InputStream inputStreamForBase64 = new FileInputStream(caminhoImagem)) {
                // Converte o InputStream para um array de bytes
                byte[] bytes = IOUtils.toByteArray(inputStreamForBase64);

                // Codifica o array de bytes em Base64
                String base64image = Base64.getEncoder().encodeToString(bytes);

                // Cria e salva a entidade GymImageEntity com o ID do GridFS e a imagem Base64
                GymImageEntity gymImageEntity = new GymImageEntity(nomeUsuario, localDateTime, gridFsId);
                gymImageRepository.save(gymImageEntity);

                // Cria o DTO para o PDF
                GerarPdfDTO gerarPdfDTO = new GerarPdfDTO(
                        gymImageEntity.getImageId(),
                        gymImageEntity.getUser(),
                        gymImageEntity.getDate(),
                        base64image
                );

                List<GerarPdfDTO> gerarPdfDTOS = new ArrayList<>();
                gerarPdfDTOS.add(gerarPdfDTO);
                GerarPdfDtoList gerarPdfDtoList = new GerarPdfDtoList(gerarPdfDTOS);

                return gerarPdfDtoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public GerarPdfDtoList obterImagemPorData(LocalDate date) {
        List<GerarPdfDTO> gerarPdfDTOs = new ArrayList<>();

        try {

            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            // Buscar todas as entidades com o intervalo de datas especificado
            List<GymImageEntity> imageEntities = gymImageRepository.findByDateRange(startOfDay, endOfDay);

            for (GymImageEntity entity : imageEntities) {
                String gridFsId = entity.getGridFsId();

                // Recuperar o arquivo do GridFS usando o gridFsId
                GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(gridFsId)));

                if (gridFSFile != null) {
                    // Obter o conteúdo do arquivo como array de bytes
                    byte[] imageData = IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream());
                    String imageBase64 = Base64.getEncoder().encodeToString(imageData);

                    // Criar o GerarPdfDTO
                    GerarPdfDTO gerarPdfDTO = new GerarPdfDTO(
                            entity.getImageId(),
                            entity.getUser(),
                            entity.getDate(),
                            imageBase64
                    );

                    gerarPdfDTOs.add(gerarPdfDTO);
                } else {
                    System.out.println("Arquivo não encontrado no GridFS para o gridFsId: " + gridFsId);
                }
            }

            // Criar e retornar o GerarPdfDtoList
            return new GerarPdfDtoList(gerarPdfDTOs);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public GerarPdfDtoList obterAll() {
        List<GerarPdfDTO> gerarPdfDTOs = new ArrayList<>();

        try {

            // Buscar todas as entidades com o intervalo de datas especificado
            List<GymImageEntity> imageEntities = gymImageRepository.findAll();

            for (GymImageEntity entity : imageEntities) {
                String gridFsId = entity.getGridFsId();

                // Recuperar o arquivo do GridFS usando o gridFsId
                GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(gridFsId)));

                if (gridFSFile != null) {
                    // Obter o conteúdo do arquivo como array de bytes
                    byte[] imageData = IOUtils.toByteArray(gridFsTemplate.getResource(gridFSFile).getInputStream());
                    String imageBase64 = Base64.getEncoder().encodeToString(imageData);

                    // Criar o GerarPdfDTO
                    GerarPdfDTO gerarPdfDTO = new GerarPdfDTO(
                            entity.getImageId(),
                            entity.getUser(),
                            entity.getDate(),
                            imageBase64
                    );

                    gerarPdfDTOs.add(gerarPdfDTO);
                } else {
                    System.out.println("Arquivo não encontrado no GridFS para o gridFsId: " + gridFsId);
                }
            }

            // Criar e retornar o GerarPdfDtoList
            return new GerarPdfDtoList(gerarPdfDTOs);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
