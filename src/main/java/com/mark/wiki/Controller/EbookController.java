package com.mark.wiki.Controller;

import com.mark.wiki.domain.Ebook;
import com.mark.wiki.resp.CommonResp;
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
    public CommonResp<List<Ebook>> list(String name) {
        CommonResp<List<Ebook>> commonResp = new CommonResp<>();
        List<Ebook> ebookList = ebookService.list();
        commonResp.setContent(ebookList);
        return commonResp;
    }
}
