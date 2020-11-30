package com.baizhi.serviceImpl;


import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPOS;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOSSUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {

    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Resource
    VideoMapper videoMapper;

    @Resource
    HttpServletRequest request;


    @DelCahe
    @AddLog("(video)(后台)增")
    //后台：增
    public String save(Video video){
        String uuid = UUID.randomUUID().toString();
        video.setId(uuid);
        video.setUploadTime(new Date());
        video.setState(0);
        videoMapper.insertSelective(video);
        return uuid;
    }

    @DelCahe
    @AddLog("(video)(后台)删")
    //后台：删
    @Override
    public void delete(Video video) {

        //根据id查询数据
        Video videos = videoMapper.selectByPrimaryKey(video);

        String videoPath = videos.getVideoPath().replace("https://msyzs.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = videos.getCoverPath().replace("https://msyzs.oss-cn-beijing.aliyuncs.com/", "");

        //2.删除视频   432425435-xxx.mp4
        AliyunOSSUtil.deleteFile("msyzs",videoPath);
        //3.删除封面
        AliyunOSSUtil.deleteFile("msyzs",coverPath);
        //1.删除数据
        videoMapper.deleteByPrimaryKey(video);

    }


    //改
    public void update(Video video){

    }


    @AddLog("(video)(后台)分页查询所有")
    //后台：分页查询所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> findAll(Integer page, Integer rows) {
        int start = (page-1)*rows;
        VideoExample videoExample = new VideoExample();
        RowBounds rowBounds = new RowBounds(start,rows);
        return videoMapper.selectByExampleAndRowBounds(videoExample,rowBounds);
    }


    @AddLog("(video)(后台)查询总条数")
    //后台：查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public long findTotalCounts() {
        Video video = new Video();
        return videoMapper.selectCount(video);
    }

    @DelCahe
    @AddLog("(video)(后台)添加")
    //后台：添加
    @Override
    public void headUpload(MultipartFile videoPath, String id) {

        //1.视频上传至阿里云   字节数组
        //获取文件名
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;

        //拼接视频文件夹
        String videoName="video/"+newName;

        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(videoPath,"msyzs",videoName);


        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";

        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
        AliyunOSSUtil.interceptVideoCover("msyzs", videoName,coverName);


        //4.修改视频的信息
        Video video = new Video();
        video.setId(id);

        video.setVideoPath("https://msyzs.oss-cn-beijing.aliyuncs.com/"+videoName);
        video.setCoverPath("https://msyzs.oss-cn-beijing.aliyuncs.com/"+coverName);

        videoMapper.updateByPrimaryKeySelective(video);

    }


    //前台：查询
    @Override
    public List<VideoPO> queryByReleaseTime() {

        List<VideoPO> videoPOList = videoMapper.queryByReleaseTime();

//        for (VideoPO videoPO : videoPOList) {
//            //获取视频id
//            String id = videoPO.getId();
//
//            //根据视频id redis 查询点赞数
//            videoPO.setLikeCount(8);
//        }

        return videoPOList;
    }

    //前台模糊查询
    @Override
    public List<VideoPOS> queryname(String content) {
        return videoMapper.queryname(content);
    }

    //前台二级类别id查询
    @Override
    public List<VideoPOS> queryid(String cateId) {
        return videoMapper.queryid(cateId);
    }


}
