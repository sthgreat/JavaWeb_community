package dzkjdx.jsb.web_community.cache;

import com.github.pagehelper.util.StringUtil;
import dzkjdx.jsb.web_community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO life = new TagDTO();
        life.setCategoryName("生活"); //栏目
        life.setTags(Arrays.asList("美食","旅行","冒险")); //小标签
        tagDTOS.add(life);

        TagDTO work = new TagDTO();
        work.setCategoryName("工作");
        work.setTags(Arrays.asList("吐槽","前途"));
        tagDTOS.add(work);


        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
