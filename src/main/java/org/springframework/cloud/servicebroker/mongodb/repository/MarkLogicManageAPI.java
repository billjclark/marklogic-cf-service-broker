package org.springframework.cloud.servicebroker.mongodb.repository;

import feign.Headers;
import feign.RequestLine;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Repository
public interface MarkLogicManageAPI {

    @RequestLine("POST /manage/v2/databases")
    public String createDatabase(@RequestBody Map<String, String> m);
}
