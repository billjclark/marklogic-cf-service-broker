package com.marklogic.cf.servicebroker.repository;

import feign.Headers;
import feign.RequestLine;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Repository
public interface MarkLogicManageAPI {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /manage/v2/databases")
    public String createDatabase(@RequestBody Map<String, String> m);

    @Headers("Content-Type: application/json")
    @RequestLine("DELETE /manage/v2/databases")
    public String deleteDatabase(@RequestBody Map<String, String> m);

    @Headers("Content-Type: application/json")
    @RequestLine("POST /manage/v2/forests")
    public String createForest(@RequestBody Map<String, String> m);

    @Headers("Content-Type: application/json")
    @RequestLine("DELETE /manage/v2/forests/")
    public String deleteForest(@RequestBody Map<String, String> m);

}
