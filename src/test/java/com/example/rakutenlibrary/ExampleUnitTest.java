package com.example.rakutenlibrary;

import com.example.rakutenlibrary.models.RepositoryInfo;
import com.example.rakutenlibrary.models.RepositoryModel;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private String json = "{\n" +
            "  \"total_count\": 6,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 108369600,\n" +
            "      \"name\": \"android-perftracking\",\n" +
            "      \"description\": \"Test Description\",\n" +
            "      \"private\": false,\n" +
            "      \"language\": \"java\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void parse_json_to_model() throws JSONException{
        RepositoryModel dummyObject = getDummyObjectForTest();
        RepositoryModel parsedObject = GithubRepositoriesProvider.getInstance().parseJsonToModel(json);
        Assert.assertNotNull(parsedObject);
        Assert.assertEquals(dummyObject.getTotal_count(),parsedObject.getTotal_count());
        Assert.assertEquals(dummyObject.isIncomplete_results(),parsedObject.isIncomplete_results());
        Assert.assertEquals(dummyObject.getItems().size(),parsedObject.getItems().size());
        Assert.assertEquals(dummyObject.getItems().get(0).getId(),parsedObject.getItems().get(0).getId());
        Assert.assertEquals(dummyObject.getItems().get(0).getName(),parsedObject.getItems().get(0).getName());
        Assert.assertEquals(dummyObject.getItems().get(0).getDescription(),parsedObject.getItems().get(0).getDescription());
        Assert.assertEquals(dummyObject.getItems().get(0).getLanguage(),parsedObject.getItems().get(0).getLanguage());
    }

    private RepositoryModel getDummyObjectForTest(){
        RepositoryModel repositoryModel = new RepositoryModel();
        repositoryModel.setTotal_count(6);
        repositoryModel.setIncomplete_results(false);
        ArrayList<RepositoryInfo> repositoryInfos = new ArrayList<>();
        RepositoryInfo info = new RepositoryInfo();
        info.setId(108369600);
        info.setName("android-perftracking");
        info.setPrivate(false);
        info.setDescription("Test Description");
        info.setLanguage("java");
        repositoryInfos.add(info);
        repositoryModel.setItems(repositoryInfos);
        return repositoryModel;
    }
}