<script>
import request from "@/utils/request";
import teacherCourseOpenView from "@/views/Teacher/TeacherCourseOpenView.vue";
import * as XLSX from "xlsx";
import * as echarts from "echarts";

export default {
  name: "Person",
  computed: {
    teacherCourseOpenView() {
      return teacherCourseOpenView
    }
  },
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/user",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        for (let i = 0; i < this.tableData.length; i++) {
          if (this.tableData[i].permission === 1) {
            this.tableData[i].permission = '学生';
          }else if (this.tableData[i].permission === 2) {
            this.tableData[i].permission = '教师';
          }else if (this.tableData[i].permission === 3) {
            this.tableData[i].permission = '管理员';
          }
        }
        this.total=res.data.total;
      });
    },reset(){
      this.s_name="";
      this.s_permission="";
      this.load();
    },
    find(){
      if(this.search_mood==0){
        this.importUrl = "";
        request.get("http://localhost:9090/user",{
          params:{
            name:this.s_name,
            permission:this.s_permission,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          //console.log(res.data);
          this.tableData=res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].permission === 1) {
              this.tableData[i].permission = '学生';
            }else if (this.tableData[i].permission === 2) {
              this.tableData[i].permission = '教师';
            }else if (this.tableData[i].permission === 3) {
              this.tableData[i].permission = '管理员';
            }
          }
          this.total=res.data.total;
        });
      }else if(this.search_mood==1){
        this.importUrl = "http://localhost:9090/manager/import";
        request.get("http://localhost:9090/manager/find",{
          params:{
            mno:this.s_no,
            mname:this.s_name,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          //console.log(res.data);
          this.tableData=res.data.page;
          this.total=res.data.total;
        });
      }else if(this.search_mood==2){
        this.importUrl = "http://localhost:9090/teacher/import";
        request.get("http://localhost:9090/teacher/find",{
          params:{
            tno:this.s_no,
            tname:this.s_name,
            scname:this.s_scname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          //console.log(res.data);
          this.tableData=res.data.page;
          this.total=res.data.total;
        });
      }else if(this.search_mood==3){
        this.importUrl = "http://localhost:9090/student/import";
        request.get("http://localhost:9090/student/find",{
          params:{
            sno:this.s_no,
            sname:this.s_name,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          //console.log(res.data);
          this.tableData=res.data.page;
          this.total=res.data.total;
        });
      }

    },
    add(){
      if(this.mood==1){
        request.post("http://localhost:9090/manager/add",this.manager).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("添加成功")
            this.find();
          }else{
            this.$message.error(res.msg)
          }
          this.dialogFormVisible=false;
        })
      }else if(this.mood==2){
        request.post("http://localhost:9090/teacher/add",this.teacher).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("添加成功")
            this.dialogVisible=false;
            this.find();
          }else{
            this.$message.error(res.msg)
          }
          this.dialogFormVisible=false;
        })
      }else if(this.mood==3){
        request.post("http://localhost:9090/student/add",this.student).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("添加成功")
            this.find();
          }else{
            this.$message.error(res.msg)
          }
          this.dialogFormVisible=false;
        })
      }
    },
    update(){
      if(this.mood==1){
        request.post("http://localhost:9090/manager/update",this.manager).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("更新成功")
            this.dialogVisible=false;
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }else if(this.mood==2){
        request.post("http://localhost:9090/teacher/update",this.teacher).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("更新成功")
            this.dialogVisible=false;
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }else if(this.mood==3){
        request.post("http://localhost:9090/student/update",this.student).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("更新成功")
            this.dialogVisible=false;
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }
    },
    del(id){
      if(this.search_mood==1){
        request.delete("http://localhost:9090/manager/"+id).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("删除成功")
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }else if(this.search_mood==2){
        request.delete("http://localhost:9090/teacher/"+id).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("删除成功")
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }else if(this.search_mood==3){
        request.delete("http://localhost:9090/student/"+id).then(res=>{
          if(res.status==="SUCCESS"){
            this.$message.success("删除成功")
            this.find();
          }else{
            this.$message.error(res.msg)
          }
        })
      }
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      //console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      //console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },save(){
      //this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = false
    },saveFile(){
      this.fileLoadVisible=false;
    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogUpadateVisible=false;
      this.dialogFormVisible = false;
      this.dialogSelectVisible = false;
    },pre(moods,item){
      if(moods==1){
        this.mood="管理员";
        this.manager.mname=item.mname;
        this.manager.mno=item.mno;
      }else if(moods==2){
        this.mood="教师";
        this.teacher.scno=item.scno;
        this.teacher.tname=item.tname;
        this.teacher.tno=item.tno;
        this.teacher.tsex=item.tsex;
        this.teacher.ttitle=item.ttitle;
      }else if(moods==3){
        this.mood="学生";
        this.student.clno=item.clno;
        this.student.sage=item.sage;
        this.student.sgrade=item.sgrade;
        this.student.sname=item.sname;
        this.student.sno=item.sno;
        this.student.ssex=item.ssex;
      }
    },exports(){
      let url = "";
      if(this.search_mood==0) url = "http://localhost:9090/user";
      else if(this.search_mood==1) url = "http://localhost:9090/manager/find";
      else if(this.search_mood==2) url = "http://localhost:9090/teacher/find";
      else if(this.search_mood==3) url = "http://localhost:9090/student/find";
      request.get(url,{
        params:{
          pageNum:1,
          pageSize:10000,
        }
      }).then(res=>{
        this.tableData=res.data.page;
        for (let i = 0; i < this.tableData.length; i++) {
          if (this.tableData[i].permission === 1) {
            this.tableData[i].permission = '学生';
          }else if (this.tableData[i].permission === 2) {
            this.tableData[i].permission = '教师';
          }else if (this.tableData[i].permission === 3) {
            this.tableData[i].permission = '管理员';
          }
        }
        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        if(this.search_mood==0)XLSX.writeFile(wb, '全部信息.xlsx');
        else if(this.search_mood==1)XLSX.writeFile(wb, '管理员信息.xlsx');
        else if(this.search_mood==2)XLSX.writeFile(wb, '教师信息.xlsx');
        else if(this.search_mood==3)XLSX.writeFile(wb, '学生信息.xlsx');
        this.find();
      });
    }, handleFileUploadSuccess(res) {
      console.log(this.importUrl);
      if(res.status==="SUCCESS"){
        this.$message.success("添加成功")
        this.fileLoadVisible=false
        this.load()
      }else{
        this.$message.error(res.msg)
        this.load()
      }
    },template(){
      if(this.search_mood==1){
        var Data=[{
          mno:"admin",
          mname:"admin",
          mpassword:"123456"
        },{
          mno:"admin2",
          mname:"admin2",
          mpassword:"123456"
        }
      ]
        const ws = XLSX.utils.json_to_sheet(Data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        XLSX.writeFile(wb, '管理员模板.xlsx');
      }else if(this.search_mood==2){
        var Data=[
          {
            tno:"10002",
            tname:"张一",
            tsex:"男",
            ttitle:"教授",
            scno:"6",
            tpassword:"123456"
          },{
            tno:"10003",
            tname:"张二",
            tsex:"男",
            ttitle:"讲师",
            scno:"6",
            tpassword:"123456"
          },{
            tno:"10004",
            tname:"张三",
            tsex:"男",
            ttitle:"教授",
            scno:"6",
            tpassword:"123456"
          },{
            tno:"10005",
            tname:"张四",
            tsex:"男",
            ttitle:"副教授",
            scno:"6",
            tpassword:"123456"
          }
        ]
        const ws = XLSX.utils.json_to_sheet(Data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        XLSX.writeFile(wb, '教师模板.xlsx');
      }else if(this.search_mood==3){
        var Data=[
          {
            sno:"21373333",
            sname:"张学生1",	
            sage:12,
            ssex:"女",
            sgrade:2021,	
            scredit:0,
            spassword:"abccc",
            clno:"210604"
          },{
            sno:"21373334",
            sname:"张学生2",	
            sage:12,
            ssex:"女",
            sgrade:2021,	
            scredit:0,
            spassword:"abccc",
            clno:"210604"
          },{
            sno:"21373335",
            sname:"张学生3",	
            sage:12,
            ssex:"女",
            sgrade:2021,	
            scredit:0,
            spassword:"abccc",
            clno:"210604"
          },{
            sno:"21373336",
            sname:"张学生4",	
            sage:23,
            ssex:"男",
            sgrade:2021,	
            scredit:0,
            spassword:"abccc",
            clno:"210604"
          },
        ]
        const ws = XLSX.utils.json_to_sheet(Data);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        XLSX.writeFile(wb, '学生模板.xlsx');
      }
      
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:5,
      s_name:"",
      s_permission:"",
      s_no:"",
      s_scname:"",
      dialogVisible: false,
      path:"",
      id:"",
      page:1,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      dialogFormVisible:false,
      fileLoadVisible:false,
      importUrl:"",
      form:{},
      user:{},
      manager:{
        mno:"",
        mname:"",
        mpassword:"",
      },
      student:{
        sno:"",
        sname:"",
        sage:"",
        ssex:"",
        sgrade:"",
        spassword:"",
        clno:"",
      },
      teacher:{
        tno:"",
        tname:"",
        ttitle:"",
        tsex:"",
        scno:"",
        tpassword:"",
      },
      mood:"",
      dialogUpadateVisible:false,
      search_mood:"0",
      select_sno:"",
      myChart:null,
      chartDom:null,
      dialogSelectVisible:false,
      header:{
        token:localStorage.getItem("token"),
      }
    }
  },
  created() {
    //console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.load();
  },mounted() {
    this.option = {
      title: {
        text: '学生学习情况',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          saveAsImage: { show: true }
        }
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          type: 'pie',
          radius: '50%',
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
  },watch: {
    search_mood(newValue, oldValue) {
    this.find();
  },select_sno(){
      
      this.$nextTick(() => {
        
        this.chartDom = document.getElementById('main');
        this.myChart = echarts.init(this.chartDom);
        request.get("http://localhost:9090/echarts/getratesno",{
          params:{
            sno:this.select_sno
          }
        }).then(res => {
              console.log(res)
              this.option.series[0].data = res.data;
              this.myChart.setOption(this.option);
            }
        )

      });
    }
},
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/manager" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href='/manager/manageall' >人员管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-select style="flex:1;width:200px" v-model="search_mood" placeholder="请选择身份" suffix-icon="el-icon-search">
        <el-option label="全部" value="0"></el-option>
        <el-option label="管理员" value="1"></el-option>
        <el-option label="教师" value="2"></el-option>
        <el-option label="学生" value="3"></el-option>
      </el-select>
      <el-input style="flex:1;width:200px"  placeholder="请输入人员名" suffix-icon="el-icon-search" v-model="s_name"
                clearable></el-input>
      <el-input style="flex:1;width:200px"  placeholder="请输入人员学/工号" suffix-icon="el-icon-search" v-model="s_no"
                clearable v-if="search_mood!=0"></el-input>
      <el-input style="flex:1;width:200px"  placeholder="请输入人员学院名" suffix-icon="el-icon-search" v-model="s_scname"
                clearable v-if="search_mood==2"></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" >新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" v-if="search_mood>0">文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports" >信息导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="permission" label="身份" width="400"  v-if="search_mood==0">
      </el-table-column>
      <el-table-column prop="sysUsername" label="学工号" width="400"  v-if="search_mood==0">
      </el-table-column>
      <el-table-column prop="sysPassword" label="密码" width="300"  v-if="search_mood==0">
      </el-table-column>
      <el-table-column prop="mno" label="工号" width="300" v-if="search_mood==1">
      </el-table-column>
      <el-table-column prop="mname" label="管理员名" width="300" v-if="search_mood==1">
      </el-table-column>
<!--      <el-table-column prop="sys_password" label="密码" width="300" v-if="search_mood==1">-->
<!--      </el-table-column>-->
      <el-table-column prop="tno" label="工号" width="150" v-if="search_mood==2">
      </el-table-column>
      <el-table-column prop="tname" label="姓名" width="150" v-if="search_mood==2">
      </el-table-column>
      <el-table-column prop="tsex" label="性别" width="100" v-if="search_mood==2">
      </el-table-column>
      <el-table-column prop="ttitle" label="职称" width="150" v-if="search_mood==2">
      </el-table-column>
      <el-table-column prop="scno" label="院系代码" width="150" v-if="search_mood==2">
      </el-table-column>
      <el-table-column prop="scname" label="学院" width="150" v-if="search_mood==2">
      </el-table-column>
<!--      <el-table-column prop="tpassword" label="密码" width="150" v-if="search_mood==2">-->
<!--      </el-table-column>-->
      <el-table-column prop="sno" label="学号" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column prop="sname" label="姓名" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column prop="sage" label="年龄" width="120" v-if="search_mood==3" >
      </el-table-column>
      <el-table-column prop="ssex" label="性别" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column prop="sgrade" label="年级" width="120" v-if="search_mood==3" >
      </el-table-column>
      <el-table-column prop="scredit" label="总学分" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column prop="clno" label="班级" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column prop="scname" label="学院" width="120" v-if="search_mood==3">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" v-if="search_mood!=0">
          <el-button
                     size="mini"
                     type="primary"
                     @click="pre(search_mood,scope.row);dialogUpadateVisible=true;" >更改信息</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="del(scope.row.mno)" v-if="search_mood==1">删除</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="del(scope.row.tno)" v-if="search_mood==2">删除</el-button>
          <el-button
                     size="mini"
                     type="primary"
                     @click="select_sno=scope.row.sno;dialogSelectVisible=true;" v-if="search_mood==3">学生学习情况</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="del(scope.row.sno)" v-if="search_mood==3">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <div style="padding:10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="人员信息" :visible.sync="dialogFormVisible" width="30%" :before-close="handleClose">
      <el-form  :model="user" class="demo-form-inline">
        <el-form-item label="身份">
          <el-select v-model="mood" placeholder="请选择身份">
            <el-option label="管理员" value="1"></el-option>
            <el-option label="教师" value="2"></el-option>
            <el-option label="学生" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工号" v-if="mood==1">
          <el-input v-model="manager.mno" placeholder="请输入工号"></el-input>
        </el-form-item>
        <el-form-item label="管理员名" v-if="mood==1">
          <el-input v-model="manager.mname" placeholder="请输入管理员名"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==1">
          <el-input v-model="manager.mpassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="工号" v-if="mood==2">
          <el-input v-model="teacher.tno" placeholder="请输入工号"></el-input>
        </el-form-item>
        <el-form-item label="姓名" v-if="mood==2">
          <el-input v-model="teacher.tname" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" v-if="mood==2">
          <el-input v-model="teacher.tsex" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="职称" v-if="mood==2">
          <el-input v-model="teacher.ttitle" placeholder="请输入职称"></el-input>
        </el-form-item>
        <el-form-item label="院系" v-if="mood==2">
          <el-input v-model="teacher.scno" placeholder="请输入所属院系"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==2">
          <el-input v-model="teacher.tpassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="学号" v-if="mood==3">
          <el-input v-model="student.sno" placeholder="请输入学号"></el-input>
        </el-form-item>
        <el-form-item label="姓名" v-if="mood==3">
          <el-input v-model="student.sname" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" v-if="mood==3">
          <el-input v-model="student.ssex" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="年龄" v-if="mood==3">
          <el-input v-model="student.sage" placeholder="请输入年龄" type="number"></el-input>
        </el-form-item>
        <el-form-item label="年级" v-if="mood==3">
          <el-input v-model="student.sgrade" placeholder="请输入年级" type="number"></el-input>
        </el-form-item>
        <el-form-item label="班级" v-if="mood==3">
          <el-input v-model="student.clno" placeholder="请输入班级"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==3">
          <el-input v-model="student.spassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">添 加</el-button>
          <el-button type="warning" @click="dialogFormVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="人员信息" :visible.sync="dialogUpadateVisible" width="30%" :before-close="handleClose">
      <el-form  :model="user" class="demo-form-inline">
        <el-form-item label="身份">
          <el-select v-model="mood" placeholder="请选择身份">
            <el-option label="管理员" value="1"></el-option>
            <el-option label="教师" value="2"></el-option>
            <el-option label="学生" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号" v-if="mood==1">
          <el-input v-model="manager.mno" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="管理员名" v-if="mood==1">
          <el-input v-model="manager.mname" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==1">
          <el-input v-model="manager.mpassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="工号" v-if="mood==2">
          <el-input v-model="teacher.tno" placeholder="请输入工号"></el-input>
        </el-form-item>
        <el-form-item label="姓名" v-if="mood==2">
          <el-input v-model="teacher.tname" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" v-if="mood==2">
          <el-input v-model="teacher.tsex" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="职称" v-if="mood==2">
          <el-input v-model="teacher.ttitle" placeholder="请输入职称"></el-input>
        </el-form-item>
        <el-form-item label="院系" v-if="mood==2">
          <el-input v-model="teacher.scno" placeholder="请输入所属院系"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==2">
          <el-input v-model="teacher.tpassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="学号" v-if="mood==3">
          <el-input v-model="student.sno" placeholder="请输入学号"></el-input>
        </el-form-item>
        <el-form-item label="姓名" v-if="mood==3">
          <el-input v-model="student.sname" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" v-if="mood==3">
          <el-input v-model="student.ssex" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="年龄" v-if="mood==3">
          <el-input v-model="student.sage" placeholder="请输入年龄"></el-input>
        </el-form-item>
        <el-form-item label="年级" v-if="mood==3">
          <el-input v-model="student.sgrade" placeholder="请输入年级"></el-input>
        </el-form-item>
        <el-form-item label="班级" v-if="mood==3">
          <el-input v-model="student.clno" placeholder="请输入班级"></el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="mood==3">
          <el-input v-model="student.spassword" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="update">更改</el-button>
          <el-button type="warning" @click="dialogUpadateVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="学生信息" :visible.sync="dialogSelectVisible" width="30%"
               :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <div id="main" style="width: 500px; height: 400px"></div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogSelectVisible = false">关 闭</el-button>
      </div>
    </el-dialog>

    <el-dialog title="信息上传" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag :action="importUrl" multiple accept=".xls, .xlsx"
                     :on-success="handleFileUploadSuccess" :headers="header">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="template()">模板下载</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<style scoped>

</style>