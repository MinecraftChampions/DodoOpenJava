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
 * mongo���ݿ�洢
 * @author qscbm187531
 */
public class MongoDBData {
    MongoClient mongo = new MongoClient();

    MongoDatabase permGroup;

    MongoDatabase permUser;

    /**
     * ��ʼ��Ȩ��ϵͳ
     * @param host ��ַ
     * @param port �˿�
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
     * �����û�Ȩ��
     * @param perm Ȩ��
     * @param DodoId DodoId
     * @return ����false������ӳɹ���ԭ��û�����Ȩ�ޣ�������true�����Ѿ��������Ȩ��
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
     * �Ƴ��û�Ȩ��
     * @param perm Ȩ��
     * @param DodoId DodoID
     * @return ����false�����Ƴ��ɹ���ԭ�������Ȩ�ޣ�������true����ԭ��û�����Ȩ��
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
     * ����һ��Ȩ����
     * @param Group Ȩ����
     * @return true�������ɹ���false����ԭ�����������
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
     * ɾ��Ȩ����
     * @param Group Ȩ����
     * @return ����true����ɹ���false����ԭ����û�����Ȩ����
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
     * ����Ȩ�����Ȩ��
     * @param Group Ȩ����
     * @param perm Ȩ��
     * @return ����false����ԭ��û�����Ȩ�ޣ�Ҳ���ǳɹ���������true����ԭ�������Ȩ�ޣ�Ҳ����ʧ�ܣ�
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
     * ɾ��Ȩ����Ȩ��
     * @param Group Ȩ����
     * @param perm Ȩ��
     * @return ����false����ԭ��û�����Ȩ�ޣ�Ҳ����ʧ�ܣ�������true����ԭ�������Ȩ�ޣ�Ҳ���ǳɹ���
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
     * �޸�Ĭ��Ȩ����
     * @param Group Ȩ����
     * @return ����false�����޸ĳɹ���Ҳ����ԭ��û�����Ȩ���鲻��Ĭ�ϵģ�������true�����޸�ʧ�ܣ�����ԭ�����Ȩ�������Ĭ�ϵģ�
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
     * ��ȡ�û���Ȩ����
     * @param DodoId DodoID
     * @return ����Ȩ����
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
     * �޸��û�Ȩ����
     * @param DodoId DodoID
     * @param Group Ȩ����
     * @return false����ɹ���true����ʧ��
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
     * ��ȡ���Ȩ�����Ȩ���б�
     * @param Group Ȩ����
     * @return Ȩ���б�
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
     * ��ȡ�û���Ȩ�ޣ��������û������Ȩ�ޣ��������û��������Ȩ�ޣ�
     * @param DodoId DodoId
     * @return ����Ȩ���б�
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
