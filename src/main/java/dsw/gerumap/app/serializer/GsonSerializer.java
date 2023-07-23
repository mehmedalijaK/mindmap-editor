package dsw.gerumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.repository.composite.MapNode;
import dsw.gerumap.app.repository.implementation.MindMap;
import dsw.gerumap.app.repository.implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(MapNode.class, new ModifiedSerializer()).create();

    @Override
    public Project loadProject(File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            return gson.fromJson(fileReader, Project.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fileReader.close();
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void saveProject(Project project) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(project.getFilePath());
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                writer.close();
            }
           catch(Exception e){

            }
        }
    }

    @Override
    public MindMap loadMindMap(File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            return gson.fromJson(fileReader, MindMap.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fileReader.close();
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void saveMindMap(MindMap mindmap) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(mindmap.getFilePath());
            gson.toJson(mindmap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                writer.close();
            }
            catch(Exception e){

            }
        }
    }
}
