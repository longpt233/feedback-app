## content
__full utilities__:
- [x] show feedback (sort by day... ?)
- [] login as admin (user dont need to login )
- [] receive 

## lib 
- jdk 13.0.1
- javafx 11.0.2 (final LTS ?): https://gluonhq.com/products/javafx/
- 

## how to use 
- tutorials:https://www.jetbrains.com/help/idea/javafx.html
- VM run config :--module-path "PATH_TO javafx-sdk-11.0.1\lib" --add-modules=javafx.controls,javafx.fxml

## error log 
- PATH_TO_FX not config global, must in Appearance & Behavior->Path Variables ?
- k load dc classforname: add connector

## gradle
"""
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.8'
}

group 'org.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

javafx {
    version = "11.0.2"
    modules = [ 'javafx.controls' ]
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
"""

