package com.mohammed.url_shortner.services;

import com.mohammed.url_shortner.models.URLInfo;
import com.mohammed.url_shortner.repositories.URLInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class URLInfoService {

    final private URLInfoRepository urlInfoRepository;
    private Environment env;

    @Autowired
    public URLInfoService(URLInfoRepository urlInfoRepository, Environment env){
        this.urlInfoRepository = urlInfoRepository;
        this.env = env;
    }

    public URLInfo getById(long id) {
        return urlInfoRepository.getReferenceById(id);
    }

    public List<URLInfo> findByShortenUrl(String shortenUrl){
        String root = env.getProperty("myApp.url");
        shortenUrl = root + shortenUrl;
        return urlInfoRepository.findByShortenUrl(shortenUrl);
    }

    public List<URLInfo> findAll() {
        return urlInfoRepository.findAll();
    }

    public URLInfo createURLInfo(String original_url){
        String root = env.getProperty("myApp.url");
        String uniqueIdentifier = generateUniqueIdentifier();
        String shorten_url = root + uniqueIdentifier;
        URLInfo urlInfo = new URLInfo(shorten_url, original_url);
        return createURLInfo(urlInfo);
    }

    public URLInfo createURLInfo(URLInfo urlInfo){
        return urlInfoRepository.saveAndFlush(urlInfo);
    }

    public String generateUniqueIdentifier() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();
        UUID idThree = UUID.randomUUID();
        UUID idFour = UUID.randomUUID();

        String time = idOne.toString().replace("-", "");
        String time2 = idTwo.toString().replace("-", "");
        String time3 = idThree.toString().replace("-", "");
        String time4 = idFour.toString().replace("-", "");

        StringBuffer data = new StringBuffer();
        data.append(time);
        data.append(time2);
        data.append(time3);
        data.append(time4);

        SecureRandom random = new SecureRandom();
        int beginIndex = random.nextInt(100);       //Begin index + length of your string < data length
        int endIndex = beginIndex + 10;            //Length of string which you want

        String uniqueIdentifier = data.substring(beginIndex, endIndex);
        return uniqueIdentifier;
    }

}
