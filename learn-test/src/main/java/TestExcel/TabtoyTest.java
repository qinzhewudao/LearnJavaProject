package TestExcel;

import com.alibaba.fastjson.JSON;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheyang
 * @date 2021/6/24 4:08 下午
 */
public class TabtoyTest {

    // 从文件读取数据
    private static String readFileAsString(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws Exception {

        // 从文件读取配置表
        String data = null;
        try {
            data = readFileAsString("table_gen.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 表格数据
        Table tab;

        // 从json序列化出对象
        tab = JSON.parseObject(data, Table.class);

        if (tab == null) {
            throw new Exception("parse table failed");
        }

        // 构建索引
        tab.BuildData();

        // 测试输出
        for (Map.Entry<Integer, Table.ExampleData> def : tab.ExampleDataByID.entrySet()) {
            System.out.println(def.getValue().Name);
        }
    }

}

class Table {
    public enum ActorType {
        None(0), //
        Pharah(1), // 法鸡
        Junkrat(2), // 狂鼠
        Genji(3), // 源氏
        Mercy(4), // 天使
        ;

        ActorType(int v) {
            this.ActorType = v;
        }

        public int getActorType() {
            return ActorType;
        }

        private final int ActorType;

        public static ActorType fromInt(int v) {
            switch (v) {
                case 0:
                    return None;
                case 1:
                    return Pharah;
                case 2:
                    return Junkrat;
                case 3:
                    return Genji;
                case 4:
                    return Mercy;
                default:
                    return null;
            }
        }

    }


    public class ExampleData {
        public int ID = 0; // 任务ID;
        public int ID2 = 0; // 任务ID2;
        public String Name = ""; // 名称;
        public float Rate = 0; // 比例;
        public double Accuracy = 0; // 精度;
        public ActorType Type = ActorType.None; // 类型;
        public int[] Skill = new int[]{}; // 技能列表;
        public int Buff = 0; // 增益;
        public String[] TagList = new String[]{}; // 标记;
        public int[] Multi = new int[]{}; // 多列;
    }

    public class ExtendData {
        public float Additive = 0; // 附加;
        public int Index2 = 0; // 索引2;
    }

    public class ExampleKV {
        public String ServerIP = ""; // 服务器IP;
        public int ServerPort = 0; // 服务器端口;
        public int[] GroupID = new int[]{}; // 分组;
    }


    public List<ExampleData> ExampleData = new ArrayList<>(); // table: ExampleData
    public List<ExtendData> ExtendData = new ArrayList<>(); // table: ExtendData
    public List<ExampleKV> ExampleKV = new ArrayList<>(); // table: ExampleKV

    // Indices
    public Map<Integer, ExampleData> ExampleDataByID = new HashMap<>(); // table: ExampleData
    public Map<Integer, ExampleData> ExampleDataByID2 = new HashMap<>(); // table: ExampleData
    public Map<Integer, ExtendData> ExtendDataByIndex2 = new HashMap<>(); // table: ExtendData

    // table: ExampleKV
    public ExampleKV GetKeyValue_ExampleKV() {
        return ExampleKV.get(0);
    }

    public interface TableEvent {
        void OnPreProcess();

        void OnPostProcess();
    }

    // Handlers
    private List<TableEvent> eventHandlers = new ArrayList<TableEvent>();

    // 注册加载后回调
    public void RegisterEventHandler(TableEvent ev) {
        eventHandlers.add(ev);
    }

    // 清除索引和数据, 在处理前调用OnPostProcess, 可能抛出异常
    public void ResetData() {

        for (TableEvent ev : eventHandlers) {
            ev.OnPreProcess();
        }

        ExampleData.clear();
        ExtendData.clear();
        ExampleKV.clear();

        ExampleDataByID.clear();
        ExampleDataByID2.clear();
        ExtendDataByIndex2.clear();
    }

    // 构建索引, 需要捕获OnPostProcess可能抛出的异常
    public void BuildData() {

        for (ExampleData v : ExampleData) {
            ExampleDataByID.put(v.ID, v);
        }
        for (ExampleData v : ExampleData) {
            ExampleDataByID2.put(v.ID2, v);
        }
        for (ExtendData v : ExtendData) {
            ExtendDataByIndex2.put(v.Index2, v);
        }

        for (TableEvent ev : eventHandlers) {
            ev.OnPostProcess();
        }
    }

}
