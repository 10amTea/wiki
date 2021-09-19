package com.mark.wiki.Controller;

import com.mark.wiki.req.EbookReq;
import com.mark.wiki.resp.CommonResp;
import com.mark.wiki.resp.EbookResp;
import com.mark.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // return string
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<List<EbookResp>> list(EbookReq req) {
        CommonResp<List<EbookResp>> commonResp = new CommonResp<>();
        List<EbookResp> ebookList = ebookService.list(req);
        commonResp.setContent(ebookList);
        return commonResp;
    }
}
