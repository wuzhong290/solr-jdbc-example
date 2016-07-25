https://cwiki.apache.org/confluence/display/solr/Apache+Solr+Reference+Guide

一、Solr+JDBC+-+SQuirreL+SQL配置
https://cwiki.apache.org/confluence/display/solr/Solr+JDBC+-+SQuirreL+SQL

二、Running+Solr+on+HDFS配置
https://cwiki.apache.org/confluence/display/solr/Running+Solr+on+HDFS

三、例子
https://github.com/markrmiller/solr-map-reduce-example.git

1、下载：http://mirror.bit.edu.cn/apache/lucene/solr/6.1.0/solr-6.1.0.tgz
2、scp solr-6.1.0.tgz  root@192.168.120.128:/home/wuzhong/solr-6.1.0.tgz
3、tar xzf solr-6.1.0.tgz solr-6.1.0/bin/install_solr_service.sh --strip-components=2
4、 ./install_solr_service.sh solr-6.1.0.tgz(等同于：./install_solr_service.sh solr-6.1.0.tgz -i /opt -d /var/solr -u solr -s solr -p 8984)
./install_solr_service.sh -help
sudo service solr status
5、service solr start
sudo service solr start -s solr1 -p 8985 -z 127.0.0.1:2181/solr1
---6、service solr start -c -Dsolr.directoryFactory=HdfsDirectoryFactory  -Dsolr.lock.type=hdfs  -Dsolr.hdfs.home=hdfs://master.indexer.com:8020/solr1


-Dsolr.directoryFactory=HdfsDirectoryFactory  -Dsolr.lock.type=hdfs  -Dsolr.hdfs.home=hdfs://master.spark.com:8020/solr1 -Dsolr.hdfs.confdir=/var/run/cloudera-scm-agent/process/100-solr-SOLR_SERVER/hadoop-conf
SOLR_OPTS="$SOLR_OPTS -Dsolr.directoryFactory=HdfsDirectoryFactory"
SOLR_OPTS="$SOLR_OPTS -Dsolr.lock.type=hdfs"

master.spark.com:2181/solr1
6、 /etc/default/solr.in.sh修改
SOLR_JAVA_MEM="-Xms1g -Xmx1g"
chmod  1777 temp
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/temp/dump

ZK_HOST=127.0.0.1:2181/solr1（SolrCloud需要配置）
chmod +x /opt/solr/server/scripts/cloud-scripts/zkcli.sh
/opt/solr1/server/scripts/cloud-scripts/zkcli.sh -zkhost 127.0.0.1:9983 -cmd makepath /solr1

/opt/solr1/server/scripts/cloud-scripts/zkcli.sh -zkhost 127.0.0.1:2181 -cmd list


./solr create -c test -n basic
./solr create -c mycollection1 -shards 2 -replicationFactor 2
./solr restart -c -p 8987 -z master.spark.com:2181/solr1 -s /var/solr1/data1 -Dsolr.ssl.checkPeerName=false
solr start -cloud -s example/cloud/node3/solr -p 8987 -z localhost:9983
Enable Remote JMX Access
ENABLE_REMOTE_JMX_OPTS=true
RMI_PORT=18983


hadoop dfsadmin -safemode leave

solr restart -c -Dsolr.directoryFactory=HdfsDirectoryFactory -Dsolr.lock.type=hdfs -Dsolr.hdfs.home=hdfs://127.0.0.1:8020/solr1 -Dsolr.hdfs.confdir=/etc/hadoop/conf
solr start -s solr1 -p 8985 -z 127.0.0.1:2181/solr1

java -Xmx512m -DzkRun -DnumShards=2 -Dsolr.directoryFactory=solr.HdfsDirectoryFactory -Dsolr.lock.type=hdfs -Dsolr.hdfs.home=hdfs://127.0.0.1:8020/solr1 -Dsolr.hdfs.confdir=/etc/hadoop/conf -DSTOP.PORT=7983 -DSTOP.KEY=key -jar start.jar --module=http 1>server.log 2>&1 &
java -DSTOP.PORT=7983 -DSTOP.KEY=key -jar start.jar --module=http --stop
java -Xmx512m -DzkRun -DnumShards=2 -Dsolr.directoryFactory=solr.HdfsDirectoryFactory -Dsolr.lock.type=hdfs -Dsolr.hdfs.home=hdfs://127.0.0.1:8020/solr1 -Dsolr.hdfs.confdir=/etc/hadoop/conf -DSTOP.PORT=7983 -DSTOP.KEY=key -jar start.jar --module=http 1>server.log 2>&1 &

cd ../server2
java -DSTOP.PORT=6574 -DSTOP.KEY=key -jar start.jar --module=http --stop
java -Xmx512m -Djetty.port=7574 -DzkHost=127.0.0.1:9983 -DnumShards=2 -Dsolr.directoryFactory=solr.HdfsDirectoryFactory -Dsolr.lock.type=hdfs -Dsolr.hdfs.home=hdfs://127.0.0.1:8020/solr2 -Dsolr.hdfs.confdir=/etc/hadoop/conf -DSTOP.PORT=6574 -DSTOP.KEY=key -jar start.jar --module=http 1>server2.log 2>&1 &

7、
./install_solr_service.sh solr-6.1.0.tgz -s solr2 -p 8984
sudo service solr2 restart -z 127.0.0.1:2181/solr1
sudo service solr2 status
sudo service solr2 stop


 sudo -u hdfs hadoop fs -chown solr1:solr1 /solr1
 sudo -u hdfs hadoop fs -chmod 777 /solr1
/etc/default/solr2.in.sh

附件
一、安装java
scp jdk-8u40-linux-x64.rpm  root@192.168.120.129:/home/wuzhong/jdk-8u40-linux-x64.rpm
chmod +x jdk-8u40-linux-x64.rpm
rpm -ivh jdk-8u40-linux-x64.rpm
vi /root/.bashrc
export HADOOP_HOME=/usr/local/hadoop-1.0.2
export JAVA_HOME=/usr/java/jdk1.8.0_40
source /root/.bashrc


export SOLR_JAVA_HOME=/usr/java/jdk1.8.0_40

chown -R  solr1:solr1  101-solr-SOLR_SERVER


root@192.168.120.129:/var/solr1/data1