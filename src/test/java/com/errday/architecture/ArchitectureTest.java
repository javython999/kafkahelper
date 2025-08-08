package com.errday.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "com.errday.kafkahelper",
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class ArchitectureTest {

     /**
     * adapter 계층은 application 내부 구현(service 등)에 직접 의존하면 안 된다.
     * adapter 반드시 application.port.in / port.out 의 interface만 의존해야 한다.
     */
    @ArchTest
    void adapterShouldOnlyDependOnApplicationPorts(JavaClasses classes) {
        noClasses().that().resideInAPackage("..adapter..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..application.service..")
                .allowEmptyShould(true)
                .check(classes);
    }

    /**
     * application 계층은 adapter에 의존해서는 안 된다.
     * 이는 헥사고날 아키텍처에서의 의존성 역전 원칙에 위배된다.
     */
    @ArchTest
    void application(JavaClasses classes) {
        noClasses().that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..adapter..")
                .allowEmptyShould(true)
                .check(classes);
    }

    /**
     * domain 계층은 오직 application, adapter에 의존해서는 안 된다.
     * application이나 adapter 등 외부 계층에 의존하는 것은 금지된다.
     */
    @ArchTest
    void domain(JavaClasses classes) {
        noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..application..", "..adapter..")
                .allowEmptyShould(true)
                .check(classes);
    }

    @ArchTest
    void portInShouldOnlyBeUsedByAdapters(JavaClasses classes) {
        classes().that().resideInAPackage("..application.port.in..")
                .should().onlyHaveDependentClassesThat()
                .resideInAnyPackage("..application..", "..adapter..")
                .allowEmptyShould(true)
                .check(classes);
    }

    @ArchTest
    void portOutShouldOnlyBeUsedByApplication(JavaClasses classes) {
        classes().that().resideInAPackage("..application.port.out..")
                .should().onlyHaveDependentClassesThat()
                .resideInAnyPackage("..application..", "..adapter.out..")
                .allowEmptyShould(true)
                .check(classes);
    }
}

