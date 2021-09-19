package com.mark.wiki.service;

import com.mark.wiki.domain.Ebook;
import com.mark.wiki.domain.EbookExample;
import com.mark.wiki.mapper.EbookMapper;
import com.mark.wiki.req.EbookReq;
import com.mark.wiki.resp.EbookResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> res = new ArrayList<>();
        ebookList.stream().forEach(x -> {
            EbookResp ebookResp = new EbookResp();
            ebookResp.setName(x.getName());
            res.add(ebookResp);
        });
        return res;
    }
}
