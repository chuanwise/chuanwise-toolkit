package cn.chuanwise.common.test;

import cn.chuanwise.common.text.Alignment;
import cn.chuanwise.common.text.TableColumn;
import cn.chuanwise.common.text.TableView;
import cn.chuanwise.common.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TableTest {
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Student {
        
        String name;
        
        int age;
        
        String desc;
    }
    
    @Test
    void testTable() {
        
        final TableView<Student> tableView = new TableView<>();
        tableView.getColumns().addAll(Arrays.asList(
            new TableColumn<>("姓名(name)", Student::getName, Alignment.LEFT),
            new TableColumn<>("年龄(age)", Student::getAge, Alignment.RIGHT),
            new TableColumn<>("描述(desc)", Student::getDesc, Alignment.CENTER)
        ));
        
        final List<Student> students = Arrays.asList(
            new Student("Chuanwise", 21, "帅哥，而且目前还是单身"),
            new Student("ThymeChen", 21, "在湖南上学"),
            new Student("Tesds ds d sds s dt", 4654421, "English long content")
        );
    
        System.out.println(tableView.display(students));
    }
}
