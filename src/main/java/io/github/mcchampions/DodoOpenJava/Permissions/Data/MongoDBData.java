package io.github.mcchampions.DodoOpenJava.Permissions.Data;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import org.json.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.github.mcchampions.DodoOpenJava.Permissions.Group;
import io.github.mcchampions.DodoOpenJava.Permissions.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * mongo数据库存储
 * @author qscbm187531
 */
public class MongoDBData {
    MongoClient mongo = new MongoClient();

    MongoDatabase permGroup;

    MongoDatabase permUser;

    /**
     * 初始化权限系统
     * @param host 地址
     * @param port 端口
     */
    public void initPermissions(String host, int port) {
        mongo = new MongoClient(host,port);
        permGroup = mongo.getDatabase("permGroup");
        permUser = mongo.getDatabase("permUser");
        List<String> listGroupsCollectionNames = (List<String>) permGroup.listCollectionNames();
        List<Group> groups = new ArrayList<>();
        for (String listGroupsCollectionName : listGroupsCollectionNames) {
            boolean Default = false;
            MongoCollection<Document> collection = permGroup.getCollection(listGroupsCollectionName);
            FindIterable<Document> find = collection.find();
            Document document = (Document) find;
            JSONObject json = new JSONObject(document.toJson());
            if (json.getBoolean("isDefault")) {
                Default = true;
            }
            List<String> perms = getGroupPermissions(listGroupsCollectionName);
            Boolean isDefault = Default;
            groups.add(new Group(perms, isDefault, listGroupsCollectionName));
        }
        Group.addGroups(groups);

        List<String> listUserCollectionNames = (List<String>) permUser.listCollectionNames();
        for (String listCollectionName : listUserCollectionNames) {
            User.addPerm(listCollectionName, getUserPermissions(listCollectionName));
            Group Group = new Group();
            for (int I = 0; I < io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().size(); I++) {
                if (Objects.equals(io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().get(I).getName(), getUserGroup(listCollectionName))) {
                    Group = io.github.mcchampions.DodoOpenJava.Permissions.Group.getGroups().get(I);
                    break;
                }
            }
            User.editUserGroup(listCollectionName, Group);
        }
    }

    /**
     * 增加用户权限
     * @param perm 权限
     * @param DodoId DodoId
     * @return 返回false代表添加成功（原本没有这个权限），返回true代表已经有了这个权限
     */
    public Boolean addUserPermission(String perm, String DodoId) {
        boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", Group.getDefaultGroup().getName());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        for (int i = 0; i < json.getJSONArray("perms").toList().size(); i++) {
            if (Objects.equals(perm, json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").toList().size();
                hasPerm = true;
            }
        }

        if (!hasPerm) {
            Bson eq = Filters.eq("Group", User.getUserGroup(DodoId).getName());
            Document doc = new Document();
            List<String> perms = BaseUtil.toStringList(json.getJSONArray("perms").toList());
            perms.add(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
    }

    /**
     * 移除用户权限
     * @param perm 权限
     * @param DodoId DodoID
     * @return 返回false代表移除成功（原本有这个权限），返回true代表原本没有这个权限
     */
    public Boolean removeUserPermission(String perm, String DodoId) {
        boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", Group.getDefaultGroup().getName());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        for (int i = 0; i < json.getJSONArray("perms").toList().size(); i++) {
            if (Objects.equals(perm, json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").toList().size();
                hasPerm = true;
            }
        }

        if (hasPerm) {
            Bson eq = Filters.eq("Group", User.getUserGroup(DodoId).getName());
            Document doc = new Document();
            List<String> perms = BaseUtil.toStringList(json.getJSONArray("perms").toList());
            perms.remove(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
    }

    /**
     * 新增一个权限组
     * @param Group 权限组
     * @return true代表创建成功，false代表原来就有这个组
     */
    public Boolean addPermGroup(String Group) {
        boolean a = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (listCollectionNames.contains(Group)) {
            a = false;
        } else {
            MongoCollection<Document> collection = permGroup.getCollection(Group);
            Document doc = new Document();
            doc.put("isDefault", false);
            doc.put("perms", null);
            collection.insertOne(doc);
        }
        return a;
    }

    /**
     * 删除权限组
     * @param Group 权限组
     * @return 返回true代表成功，false代表原本就没有这个权限组
     */
    public Boolean deletePermGroup(String Group) {
        boolean a = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (listCollectionNames.contains(Group)) {
            MongoCollection<Document> collection = permGroup.getCollection(Group);
            collection.drop();
        } else {
            a = false;
        }
        return a;
    }

    /**
     * 增加权限组的权限
     * @param Group 权限组
     * @param perm 权限
     * @return 返回false代表原本没有这个权限（也就是成功），返回true代表原本有这个权限（也就是失败）
     */
    public Boolean addGroupPerm(String Group, String perm) {
        boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("perms").toList().size(); i++) {
            if (Objects.equals(perm, json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").toList().size();
                hasPerm = true;
            }
        }

        if (!hasPerm) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> perms = BaseUtil.toStringList(json.getJSONArray("perms").toList());
            perms.add(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
    }

    /**
     * 删除权限组权限
     * @param Group 权限组
     * @param perm 权限
     * @return 返回false代表原本没有这个权限（也就是失败），返回true代表原本有这个权限（也就是成功）
     */
    public Boolean deleteGroupPerm(String Group, String perm) {
        boolean hasPerm = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("perms").toList().size(); i++) {
            if (!Objects.equals(perm, json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").toList().size();
                hasPerm = false;
            }
        }

        if (hasPerm) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> perms = BaseUtil.toStringList(json.getJSONArray("perms").toList());
            perms.remove(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
    }

    /**
     * 修改默认权限组
     * @param Group 权限组
     * @return 返回false代表修改成功（也就是原本没有这个权限组不是默认的），返回true代表修改失败（代表原本这个权限组就是默认的）
     */
    public Boolean modifyDefaultPermGroup(String Group) {
        boolean isDefault = false;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        if (Objects.equals(io.github.mcchampions.DodoOpenJava.Permissions.Group.getDefaultGroup().getName(), Group)) {
            isDefault = true;
        } else {
            MongoCollection<Document> collection = permGroup.getCollection(io.github.mcchampions.DodoOpenJava.Permissions.Group.getDefaultGroup().getName());
            Bson eq = Filters.eq("isDefault", true);
            Document doc = new Document();
            doc.put("$set",new Document("isDefault", false));
            collection.updateOne(eq, doc);
        }
        if (!isDefault) {
            MongoCollection<Document> collection = permGroup.getCollection(Group);
            Bson eq = Filters.eq("isDefault", false);
            Document doc = new Document();
            doc.put("isDefault", true);
            collection.updateOne(eq, doc);
        }
        return isDefault;
    }

    /**
     * 获取用户的权限组
     * @param DodoId DodoID
     * @return 返回权限组
     */
    public String getUserGroup(String DodoId) {
        String Group;
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        Group = json.getString("Group");
        return Group;
    }

    /**
     * 修改用户权限组
     * @param DodoId DodoID
     * @param Group 权限组
     * @return false代表成功，true代表失败
     */
    public Boolean modifyUserGroup(String DodoId, String Group) {
        boolean originalGroup = false;
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        List<String> listGroupNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", Group);
            collection.insertOne(doc);
        }
        if (!listGroupNames.contains(Group)) {
            addPermGroup(Group);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }
        if (Objects.equals(json.getString("Group"), Group)) {
            originalGroup = true;
        } else {
            Bson eq = Filters.eq("Group", getUserGroup(DodoId));
            Document doc = new Document();
            doc.put("$set",new Document("Group", Group));
            collection.updateOne(eq, doc);
        }
        return originalGroup;
    }

    /**
     * 获取这个权限组的权限列表
     * @param Group 权限组
     * @return 权限列表
     */
    public List<String> getGroupPermissions(String Group) {
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }
        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        return BaseUtil.toStringList(json.getJSONArray("perms").toList());
    }

    /**
     * 获取用户的权限（仅包括用户自身的权限，不包括用户所在组的权限）
     * @param DodoId DodoId
     * @return 返回权限列表
     */
    public List<String> getUserPermissions(String DodoId) {
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", Group.getDefaultGroup().getName());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = new JSONObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        return BaseUtil.toStringList(json.getJSONArray("perms").toList());
    }
}
