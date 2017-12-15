#源代码所在的文件
sourcePath="*****/HeleeBlog/source"
#Tomcat运行项目所在的路径
targetPath="*****/apps"
#Tomcat路径
tomcatPath="*****/tomcat"
#数据库配置文件
configFile="*****/heleeos.properties"

echo "rebuild heleeos.com"

echo "1.get code"
cd "$sourcePath"
git pull

echo "2.maven build"
mvn clean install -Dmaven.test.skip

echo "3.stop server"
cd "$tomcatPath"
sh tomcatstop.sh

echo "4.copy blog-manager"
rm -rf "$targetPath/blog-manager/WEB-INF/"
cp -r "$sourcePath/blog-manager/target/blog-manager/WEB-INF/" "$targetPath/blog-manager/"

echo "5.copy blog-web"
rm -rf "$targetPath/blog-web/WEB-INF/"
cp -r "$sourcePath/blog-web/target/blog-web/WEB-INF/" "$targetPath/blog-web/"

#复制配置文件
echo "6.copy config"
\cp -f "$configFile" "$targetPath/blog-manager/WEB-INF/classes/config.properties"
\cp -f "$configFile" "$targetPath/blog-web/WEB-INF/classes/config.properties"

#复制lib到公共路径中
echo "7.copy lib"
rm -f "$targetPath/blog-lib/*.jar"
cp "$sourcePath/blog-manager/target/blog-manager/WEB-INF/lib/*.jar" "$targetPath/blog-lib/"

#删除各自项目下无用的lib
echo "8.remove lib"
rm -rf "$targetPath/blog-manager/WEB-INF/lib"
rm -rf "$targetPath/blog-web/WEB-INF/lib"


echo "9.start blog-server"
cd "$tomcatPath"
sh tomcatstart.sh

echo "start success"