package io.github.mcchampions.DodoOpenJava;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.Objects;

/**
 * 整个权限系统
 */
public class permissions {

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
    }

    /**
     * 初始化权限系统
     * @param host 地址
     */
    public void initPermissions(String host) {
        mongo = new MongoClient(host);
        permGroup = mongo.getDatabase("permGroup");
        permUser = mongo.getDatabase("permUser");
    }

    /**
     * 增加用户权限
     * @param perm 权限
     * @param DodoId DodoId
     * @return 返回false代表添加成功（原本没有这个权限），返回true代表已经有了这个权限
     */
    public Boolean addUserPermission(String perm, String DodoId) {
        Boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", getDefaultPermGroup());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        for (int i = 0; i < json.getJSONArray("perms").size(); i++) {
            if (Objects.equals(perm, (String) json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").size();
                hasPerm = true;
            }
        }

        if (!hasPerm) {
            Bson eq = Filters.eq("Group", getUserGroup(DodoId));
            Document doc = new Document();
            List<String> perms = json.getJSONArray("perms");
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
        Boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permUser.listCollectionNames();
        if (!listCollectionNames.contains(DodoId)) {
            MongoCollection<Document> collection = permUser.getCollection(DodoId);
            Document doc = new Document();
            doc.put("Group", getDefaultPermGroup());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        for (int i = 0; i < json.getJSONArray("perms").size(); i++) {
            if (Objects.equals(perm, (String) json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").size();
                hasPerm = true;
            }
        }

        if (hasPerm) {
            Bson eq = Filters.eq("Group", getUserGroup(DodoId));
            Document doc = new Document();
            List<String> perms = json.getJSONArray("perms");
            perms.remove(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
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
            doc.put("Group", getDefaultPermGroup());
            collection.insertOne(doc);
        }
        MongoCollection<Document> collection = permUser.getCollection(DodoId);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        return (List<String>) json.getJSONArray("perms");
    }

    /**
     * 获取这个权限组的权限列表（不包括继承的权限）
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
        JSONObject json = JSONObject.parseObject(document.toJson());
        if (json.getJSONArray("perms") == null) {
            Document doc = new Document();
            doc.put("perms", null);
            collection.insertOne(doc);
        }

        return (List<String>) json.getJSONArray("perms");
    }

    /**
     * 新增一个权限组
     * @param Group 权限组
     * @return true代表创建成功，false代表原来就有这个组
     */
    public Boolean addPermGroup(String Group) {
        Boolean a = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (listCollectionNames.contains(Group)) {
            a = false;
        } else {
            MongoCollection<Document> collection = permGroup.getCollection(Group);
            Document doc = new Document();
            doc.put("isDefault", false);
            doc.put("perms", null);
            doc.put("extends", null);
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
        Boolean a = true;
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
        Boolean hasPerm = false;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("perms").size(); i++) {
            if (Objects.equals(perm, (String) json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").size();
                hasPerm = true;
            }
        }

        if (!hasPerm) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> perms = json.getJSONArray("perms");
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
        Boolean hasPerm = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("perms").size(); i++) {
            if (!Objects.equals(perm, (String) json.getJSONArray("perms").get(i))) {
                i = json.getJSONArray("perms").size();
                hasPerm = false;
            }
        }

        if (hasPerm) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> perms = json.getJSONArray("perms");
            perms.remove(perm);
            doc.put("$set",new Document("perms", perms));
            collection.updateOne(eq, doc);
        }
        return hasPerm;
    }

    /**
     * 获取默认权限组
     * @return 默认权限组
     */
    public String getDefaultPermGroup() {
        String Group = "Default";
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        for (int i = 0; i < listCollectionNames.size(); i++) {
            MongoCollection<Document> collection = permGroup.getCollection(listCollectionNames.get(i));
            FindIterable<Document> find = collection.find();
            Document document = (Document) find;
            JSONObject json = JSONObject.parseObject(document.toJson());
            if (json.getBoolean("isDefault")) {
                Group = listCollectionNames.get(i);
                i = listCollectionNames.size();
            }
        }
        return Group;
    }

    /**
     * 修改默认权限组
     * @param Group 权限组
     * @return 返回false代表修改成功（也就是原本没有这个权限组不是默认的），返回true代表修改失败（代表原本这个权限组就是默认的）
     */
    public Boolean modifyDefaultPermGroup(String Group) {
        Boolean isDefault = false;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        if (Objects.equals(getDefaultPermGroup(), Group)) {
            isDefault = true;
        } else {
            MongoCollection<Document> collection = permGroup.getCollection(getDefaultPermGroup());
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
     * 新增继承的权限组
     * @param Group 继承的权限组
     * @param ExtendGroup 被继承的权限组
     * @return false代表成功，true代表失败
     */
    public Boolean addGroupExtendGroup(String Group, String ExtendGroup) {
        Boolean hasExtend = false;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("perms").size(); i++) {
            if (Objects.equals(ExtendGroup, (String) json.getJSONArray("extends").get(i))) {
                i = json.getJSONArray("extends").size();
                hasExtend = true;
            }
        }

        if (!hasExtend) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> Extends = json.getJSONArray("extends");
            Extends.add(ExtendGroup);
            doc.put("$set",new Document("extends", Extends));
            collection.updateOne(eq, doc);
        }
        return hasExtend;
    }

    /**
     * 删除继承权限组
     * @param Group 继承的权限组
     * @param ExtendGroup 被继承的权限组
     * @return false代表失败，true代表成功
     */
    public Boolean deleteGroupExtendGroup(String Group, String ExtendGroup) {
        Boolean hasExtends = true;
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }

        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());
        for (int i = 0; i < json.getJSONArray("extends").size(); i++) {
            if (!Objects.equals(ExtendGroup, (String) json.getJSONArray("extends").get(i))) {
                i = json.getJSONArray("extends").size();
                hasExtends = false;
            }
        }

        if (hasExtends) {
            Bson eq = Filters.eq("isDefault", json.getBoolean("isDefault"));
            Document doc = new Document();
            List<String> Extends = json.getJSONArray("extends");
            Extends.remove(ExtendGroup);
            doc.put("$set",new Document("extends", Extends));
            collection.updateOne(eq, doc);
        }
        return hasExtends;
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
        JSONObject json = JSONObject.parseObject(document.toJson());
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
        Boolean originalGroup = false;
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
        JSONObject json = JSONObject.parseObject(document.toJson());
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
     * 获取一个权限组继承的所有权限组
     * @param Group 权限组
     * @return 继承的所有权限组
     */
    public List<String> getGroupExtendGroups(String Group) {
        List<String> listCollectionNames = (List<String>) permGroup.listCollectionNames();
        if (!listCollectionNames.contains(Group)) {
            addPermGroup(Group);
        }
        MongoCollection<Document> collection = permGroup.getCollection(Group);
        FindIterable<Document> find = collection.find();
        Document document = (Document) find;
        JSONObject json = JSONObject.parseObject(document.toJson());

        return (List<String>) json.getJSONArray("extends");
    }

    /**
     * 检查权限（用户本身权限，用户所在用户组权限，用户所在用户组继承的用户组的权限）
     * @param DodoId DodoID
     * @param perm 权限
     * @return true代表用户有这个权限，false代表用户没有这个权限
     */
    public Boolean checkPermission(String DodoId, String perm) {
        Boolean hasPerm = false;
        if (getUserPermissions(DodoId).contains(perm)) {
            hasPerm = true;
        } else {
            if (getGroupPermissions(getUserGroup(DodoId)).contains(perm)) {
                hasPerm = true;
            } else {
                for (int i = 0; i < getGroupExtendGroups(getUserGroup(DodoId)).size(); i++) {
                    if (getGroupPermissions(getGroupExtendGroups(getUserGroup(DodoId)).get(i)).contains(perm)) {
                        hasPerm = true;
                        i = getGroupExtendGroups(getUserGroup(DodoId)).size();
                    }
                }
            }
        }
        return hasPerm;
    }
}
