package com.supretest.service;

import com.supretest.base.domain.*;
import com.supretest.base.mapper.FileContentMapper;
import com.supretest.base.mapper.FileMetadataMapper;
import com.supretest.base.mapper.TestCaseFileMapper;
import com.supretest.commons.constants.FileType;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.LogUtil;
import com.supretest.commons.utils.RsaKey;
import com.supretest.commons.utils.RsaUtil;
import com.supretest.performance.request.QueryProjectFileRequest;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileService {
    @Resource
    private FileMetadataMapper fileMetadataMapper;
    @Resource
    private FileContentMapper fileContentMapper;
    @Resource
    private TestCaseFileMapper testCaseFileMapper;

    public byte[] loadFileAsBytes(String id) {
        FileContent fileContent = fileContentMapper.selectByPrimaryKey(id);

        return fileContent.getFile();
    }

    public FileContent getFileContent(String fileId) {
        return fileContentMapper.selectByPrimaryKey(fileId);
    }

    public void setFileContent(String fileId, byte[] content) {
        FileContent record = new FileContent();
        record.setFile(content);
        record.setFileId(fileId);
        fileContentMapper.updateByPrimaryKeySelective(record);
    }

    public void deleteFileByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        FileMetadataExample example = new FileMetadataExample();
        example.createCriteria().andIdIn(ids);
        fileMetadataMapper.deleteByExample(example);

        FileContentExample example2 = new FileContentExample();
        example2.createCriteria().andFileIdIn(ids);
        fileContentMapper.deleteByExample(example2);
    }

    public void deleteFileRelatedByIds(List<String> ids) {
        deleteFileByIds(ids);
    }

    public FileMetadata saveFile(MultipartFile file, String projectId, String fileId) {
        final FileMetadata fileMetadata = new FileMetadata();
        if (StringUtils.isEmpty(fileId)) {
            fileMetadata.setId(UUID.randomUUID().toString());
        } else {
            fileMetadata.setId(fileId);
        }
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setSize(file.getSize());
        fileMetadata.setProjectId(projectId);
        fileMetadata.setCreateTime(System.currentTimeMillis());
        fileMetadata.setUpdateTime(System.currentTimeMillis());
        FileType fileType = getFileType(fileMetadata.getName());
        fileMetadata.setType(fileType.name());
        fileMetadataMapper.insert(fileMetadata);

        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileMetadata.getId());
        try {
            fileContent.setFile(file.getBytes());
        } catch (IOException e) {
            MSException.throwException(e);
        }
        fileContentMapper.insert(fileContent);

        return fileMetadata;
    }

    public FileMetadata saveFile(MultipartFile file, String projectId) {
        return saveFile(file, projectId, null);
    }

    public FileMetadata saveFile(MultipartFile file) {
        return saveFile(file, null);
    }

    public FileMetadata saveFile(File file, byte[] fileByte) {
        final FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setId(UUID.randomUUID().toString());
        fileMetadata.setName(file.getName());
        fileMetadata.setSize(file.length());
        fileMetadata.setCreateTime(System.currentTimeMillis());
        fileMetadata.setUpdateTime(System.currentTimeMillis());
        FileType fileType = getFileType(fileMetadata.getName());
        fileMetadata.setType(fileType.name());
        fileMetadataMapper.insert(fileMetadata);

        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileMetadata.getId());
        fileContent.setFile(fileByte);
        fileContentMapper.insert(fileContent);

        return fileMetadata;
    }

    public FileMetadata insertFileByFileName(File file, byte[] fileByte, String projectId) {
        if (StringUtils.isEmpty(file.getName())) {
            return null;
        } else {
            FileMetadataExample example = new FileMetadataExample();
            example.createCriteria().andProjectIdEqualTo(projectId).andNameEqualTo(file.getName());
            List<FileMetadata> fileMetadatasInDataBase = fileMetadataMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(fileMetadatasInDataBase)) {
                final FileMetadata fileMetadata = new FileMetadata();
                fileMetadata.setId(UUID.randomUUID().toString());
                fileMetadata.setName(file.getName());
                fileMetadata.setSize(file.length());
                fileMetadata.setProjectId(projectId);
                fileMetadata.setCreateTime(System.currentTimeMillis());
                fileMetadata.setUpdateTime(System.currentTimeMillis());
                FileType fileType = getFileType(fileMetadata.getName());
                fileMetadata.setType(fileType.name());
                fileMetadataMapper.insert(fileMetadata);

                FileContent fileContent = new FileContent();
                fileContent.setFileId(fileMetadata.getId());
                fileContent.setFile(fileByte);
                fileContentMapper.insert(fileContent);
                return fileMetadata;
            } else {
                FileMetadata fileMetadata = fileMetadatasInDataBase.get(0);
                fileMetadata.setName(file.getName());
                fileMetadata.setSize(file.length());
                fileMetadata.setProjectId(projectId);
                fileMetadata.setUpdateTime(System.currentTimeMillis());
                FileType fileType = getFileType(fileMetadata.getName());
                fileMetadata.setType(fileType.name());
                fileMetadataMapper.updateByPrimaryKeySelective(fileMetadata);

                fileContentMapper.deleteByPrimaryKey(fileMetadata.getId());
                FileContent fileContent = new FileContent();
                fileContent.setFileId(fileMetadata.getId());
                fileContent.setFile(fileByte);
                fileContentMapper.insert(fileContent);
                return fileMetadata;
            }
        }

    }

    public FileMetadata saveFile(File file, byte[] fileByte, String projectId) {
        final FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setId(UUID.randomUUID().toString());
        fileMetadata.setName(file.getName());
        fileMetadata.setSize(file.length());
        fileMetadata.setProjectId(projectId);
        fileMetadata.setCreateTime(System.currentTimeMillis());
        fileMetadata.setUpdateTime(System.currentTimeMillis());
        FileType fileType = getFileType(fileMetadata.getName());
        fileMetadata.setType(fileType.name());
        fileMetadataMapper.insert(fileMetadata);

        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileMetadata.getId());
        fileContent.setFile(fileByte);
        fileContentMapper.insert(fileContent);

        return fileMetadata;
    }

    public FileMetadata saveFile(byte[] fileByte, String fileName, Long fileSize) {
        final FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setId(UUID.randomUUID().toString());
        fileMetadata.setName(fileName);
        fileMetadata.setSize(fileSize);
        fileMetadata.setCreateTime(System.currentTimeMillis());
        fileMetadata.setUpdateTime(System.currentTimeMillis());
        FileType fileType = getFileType(fileMetadata.getName());
        fileMetadata.setType(fileType.name());
        fileMetadataMapper.insert(fileMetadata);

        FileContent fileContent = new FileContent();
        fileContent.setFileId(fileMetadata.getId());
        fileContent.setFile(fileByte);
        fileContentMapper.insert(fileContent);

        return fileMetadata;
    }

    public FileMetadata copyFile(String fileId) {
        FileMetadata fileMetadata = fileMetadataMapper.selectByPrimaryKey(fileId);
        FileContent fileContent = getFileContent(fileId);
        if (fileMetadata != null && fileContent != null) {
            fileMetadata.setId(UUID.randomUUID().toString());
            fileMetadata.setCreateTime(System.currentTimeMillis());
            fileMetadata.setUpdateTime(System.currentTimeMillis());
            fileMetadataMapper.insert(fileMetadata);

            fileContent.setFileId(fileMetadata.getId());
            fileContentMapper.insert(fileContent);
        }
        return fileMetadata;
    }

    private FileType getFileType(String filename) {
        int s = filename.lastIndexOf(".") + 1;
        String type = filename.substring(s);
        return FileType.valueOf(type.toUpperCase());
    }

    public List<FileMetadata> getFileMetadataByCaseId(String caseId) {
        TestCaseFileExample testCaseFileExample = new TestCaseFileExample();
        testCaseFileExample.createCriteria().andCaseIdEqualTo(caseId);
        final List<TestCaseFile> testCaseFiles = testCaseFileMapper.selectByExample(testCaseFileExample);

        if (CollectionUtils.isEmpty(testCaseFiles)) {
            return new ArrayList<>();
        }

        List<String> fileIds = testCaseFiles.stream().map(TestCaseFile::getFileId).collect(Collectors.toList());
        FileMetadataExample example = new FileMetadataExample();
        example.createCriteria().andIdIn(fileIds);
        return fileMetadataMapper.selectByExample(example);
    }

    public void deleteFileById(String fileId) {
        deleteFileByIds(Collections.singletonList(fileId));
    }

    public FileMetadata getFileMetadataById(String fileId) {
        return fileMetadataMapper.selectByPrimaryKey(fileId);
    }

    public void updateFileMetadata(FileMetadata fileMetadata) {
        fileMetadataMapper.updateByPrimaryKeySelective(fileMetadata);
    }

    public boolean isFileExsits(String fileId) {
        FileMetadataExample example = new FileMetadataExample();
        example.createCriteria().andIdEqualTo(fileId);
        long fileCount = fileMetadataMapper.countByExample(example);
        if (fileCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<FileMetadata> getProjectFiles(String projectId, QueryProjectFileRequest request) {
        FileMetadataExample example = new FileMetadataExample();
        FileMetadataExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        if (!StringUtils.isEmpty(request.getName())) {
            criteria.andNameEqualTo(request.getName());
        }
        return fileMetadataMapper.selectByExample(example);
    }

    public List<FileMetadata> getFileMetadataByIds(List<String> fileIds) {
        if (CollectionUtils.isEmpty(fileIds)) {
            return new ArrayList<>();
        }
        FileMetadataExample example = new FileMetadataExample();
        example.createCriteria().andIdIn(fileIds);
        return fileMetadataMapper.selectByExample(example);
    }

    public RsaKey checkRsaKey() {
        String key = "ms.login.rsa.key";
        FileContent value = getFileContent(key);
        if (value == null) {
            try {
                RsaKey rsaKey = RsaUtil.getRsaKey();
                byte[] bytes = SerializationUtils.serialize(rsaKey);
                final FileMetadata fileMetadata = new FileMetadata();
                fileMetadata.setId(key);
                fileMetadata.setName(key);
                fileMetadata.setSize((long) bytes.length);
                fileMetadata.setCreateTime(System.currentTimeMillis());
                fileMetadata.setUpdateTime(System.currentTimeMillis());
                fileMetadata.setType("RSA_KEY");
                fileMetadataMapper.insert(fileMetadata);

                FileContent fileContent = new FileContent();
                fileContent.setFileId(fileMetadata.getId());
                fileContent.setFile(bytes);
                fileContentMapper.insert(fileContent);
                return rsaKey;
            } catch (Exception e) {
                LogUtil.error(e);
            }
        }
        return SerializationUtils.deserialize(value.getFile());
    }
}
