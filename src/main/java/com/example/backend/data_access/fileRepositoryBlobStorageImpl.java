package com.example.backend.data_access;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlobInputStream;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.example.backend.repositories.FileRepository;
import com.example.backend.responsemodel.GenericException;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Repository
public class fileRepositoryBlobStorageImpl implements FileRepository {
    @Override
    public byte[] downloadFileFromStorage(String fileName) {
        BlobContainerClient blobContainerClient = createBlobContainerClient();
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        try (BlobInputStream blobStream = blobClient.openInputStream()) {
            return blobStream.readAllBytes();
        } catch (IOException e) {
            throw new GenericException("File does not exist");
        }
    }

    @Override
    public String uploadFileToStorage(String fileName, byte[] file) {
        BlobContainerClient blobContainerClient = createBlobContainerClient();
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(fileName).getBlockBlobClient();
        try (ByteArrayInputStream dataStream = new ByteArrayInputStream(file)) {
            blockBlobClient.upload(dataStream, file.length);
        } catch (IOException ex) {
            throw new GenericException("Error uploading file");
        }
        return blockBlobClient.getBlobUrl();
    }

    private BlobContainerClient createBlobContainerClient() {
        String azureStorageConnectionString = "connectionString";
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(azureStorageConnectionString)
                .buildClient();
        String containerName = "forms";
        return blobServiceClient.getBlobContainerClient(containerName);
    }
}
