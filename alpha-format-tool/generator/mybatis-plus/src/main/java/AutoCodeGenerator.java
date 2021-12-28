import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xudazheng
 * @date 2021/12/16 17:06
 * @description:
 */
public class AutoCodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://172.20.135.96:3306/tenant",
            "pushdb", "SkYWOrTh$TcOs");

    /**
     * 执行 run
     */
    public static void main(String[] args) {
        // project path
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        // 输出路径
                        .outputDir(projectPath + "/alpha-format-tool/generator/mybatis-plus/src/main/java")
                        // 覆盖已生成文件
                        .fileOverride()
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？"))
                        // .parent("com.coocaa.saas.gather.admin")
                        // .entity("model.entity")
                        // .mapper("dao")
                        // .xml("dao.xml")
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok()
                        /*.addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        )*/.build())
                // 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
//                .templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 处理 all 情况
     *
     * @param tables 表名
     * @return list
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
