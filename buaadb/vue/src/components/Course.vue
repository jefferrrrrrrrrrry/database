<script>
import request from "@/utils/request";
import * as echarts from 'echarts'
import * as XLSX from "xlsx";

export default {
  name: "Course",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){

      request.get("http://localhost:9090/course/find",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname="";
      this.find();
    },
    find(){
      request.get("http://localhost:9090/course/find",{
        params:{
          cno:this.s_cno,
          cname:this.s_cname,
          tname:this.s_tname,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        ////console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      request.delete("http://localhost:9090/course/"+id, {
        params:{
          cno:id
        }
      }).then(res=>{
        //console.log(res)
        if(res.status==="SUCCESS"){
          this.$message.success("删除成功");
          this.load();
        }else{
          this.$message.error(res.msg);
        }
      })
    },
    update(){
      this.form.status="1";
      request.post("http://localhost:9090/course/update",this.form).then(res=>{
        //console.log(res)
        if(res.status==="SUCCESS"){
          this.$message.success("更新成功");
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    choose(id){
      request.post("http://localhost:9090/course/selectCourse",id).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("选课成功");
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
    },save(){
      //this.form = JSON.parse(JSON.stringify(row))
      this.form.status="1";
      if(this.loc==1){
        request.post("http://localhost:9090/course/add",this.form).then(res=>{
          //console.log(res)
          if(res.status==="SUCCESS"){
            this.$message.success("申请已递交教务审核");
            this.load();
          }else{
            this.$message.error(res.msg)
          }
        })
      }else{
        request.post("http://localhost:9090/course/manageradd",this.form).then(res=>{
          //console.log(res)
          if(res.status==="SUCCESS"){
            this.$message.success("添加成功");
            this.load();
          }else{
            this.$message.error(res.msg)
          }
        })
      }
      //this.dialogFormVisible = false
    }, formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    }, handleSizeChange(pageSize){
      ////console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      ////console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },handleFileUploadSuccess(res) {
      //console.log(res)
      if(res.status==="SUCCESS"){
        this.$message.success("添加成功")
        this.load()
      }else{
        this.$message.error(res.msg)
        this.load()
      }

    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogSelectVisible=false;
      this.dialogFormVisible = false;
      this.dialogChangeVisible=false;
    },exports(){
      request.get("http://localhost:9090/course/find",{
        params:{
          pageNum:1,
          pageSize:10000
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data.page;
        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        XLSX.writeFile(wb, '全部课程.xlsx');
        this.find();
      });
      
    },insertInfo(row) {
      this.dialogChangeVisible = true;
      this.form = JSON.parse(JSON.stringify(row));
    }, searchStudent(row) {
      this.$router.push({
        //path: "/teacher/search",
        name: 'ManagerCourseSelect',
        params: {
          cno: row.cno
        }
      })
    },template(){
      var Data;
      Data=[{
          cno:"B01",
          cname:"航空航天概论A",
          cpos:"学院路校区",
          tno:"10002",
          ctype:"必修",
          ccredit:2,
          ccapacity:50
        },{
          cno:"B02",
          cname:"航空航天概论B",
          cpos:"学院路校区",
          tno:"10002",
          ctype:"必修",
          ccredit:2,
          ccapacity:50
        },{
          cno:"B03",
          cname:"航空航天概论C",
          cpos:"学院路校区",
          tno:"10002",
          ctype:"必修",
          ccredit:2,
          ccapacity:50
        },{
          cno:"B04",
          cname:"航空航天概论D",
          cpos:"学院路校区",
          tno:"10002",
          ctype:"必修",
          ccredit:2,
          ccapacity:50
        }
      ]
      const ws = XLSX.utils.json_to_sheet(Data);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
      XLSX.writeFile(wb, '课程模板.xlsx');
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:5,
      s_cname:"",
      s_cno:"",
      s_tname:"",
      dialogVisible: false,
      path:"",
      id:"",
      loc:0,
      page:1,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      dialogFormVisible:false,
      fileLoadVisible:false,
      dialogChangeVisible:false,
      form:{},
      file:null,
      select_cno:"",
      dialogSelectVisible:false,
      option:"",
      myChart:null,
      chartDom:null,
      header:{
        token:localStorage.getItem("token"),
      }
    }
  },
  created() {
    ////console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.load();
    if(this.$route.path.includes("teacher")){
      this.id="teacher";
      this.loc=1;
    }else if(this.$route.path.includes("student")){
      this.id="student";
      this.loc=0;
    }else{
      this.id="manager";
      this.loc=2;
    }
  },mounted() {
    this.option = {
      title: {
        text: '课程优良率',
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
  },watch:{
    select_cno(){
      //console.log("666")
      this.$nextTick(() => {
        this.chartDom = document.getElementById('main');
        this.myChart = echarts.init(this.chartDom);
        request.get("http://localhost:9090/echarts/getratecno",{
          params:{
            cno:this.select_cno
          }
        }).then(res => {
              //console.log(res)
              this.option.series[0].data = res.data;
              this.myChart.setOption(this.option);
            }
        )

      });
}
    }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/student" } ' v-if="this.loc==0">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to='{ path: "/teacher" } ' v-if="this.loc==1">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to='{ path: "/manager" } ' v-if="this.loc==2">首页</el-breadcrumb-item>

      <el-breadcrumb-item v-if="this.loc==0"><a href='/student/course' >选课</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==1"><a href='/teacher/course' >全部课程</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==2"><a href='/manager/course' >课程管理</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-input style="flex:1;width:200px"  placeholder="请输入课程名" suffix-icon="el-icon-search" v-model="s_cname"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入课程代码" suffix-icon="el-icon-search" v-model="s_cno"
                clearable></el-input>
      <el-input style="flex:1 ;width:200px"  placeholder="请输入教师姓名" suffix-icon="el-icon-search" v-model="s_tname"
                clearable></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" v-if="loc==1||loc==2">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" v-if="loc==2">文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports()" >课程导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="cno" label="课程代码" width="100">
      </el-table-column>
      <el-table-column prop="cname" label="课程名称" width="120">
      </el-table-column>
      <el-table-column prop="ctype" label="课程类型" width="120">
      </el-table-column>
      <el-table-column prop="tname" label="开课教师" width="120">
      </el-table-column>
      <el-table-column prop="cpos" label="校区" width="120">
      </el-table-column>
      <el-table-column prop="ccredit" label="学分" width="80">
      </el-table-column>
      <el-table-column prop="cremain" label="剩余人数" width="80">
      </el-table-column>
      <el-table-column prop="ccapacity" label="容量" width="80">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button v-if="loc==0"
              size="mini"
              type="primary"
              @click="choose(scope.row.cno)">选课</el-button>
          <el-button v-if="loc==2"
                     size="mini"
                     type="primary"
                     @click="searchStudent(scope.row)">学生信息</el-button>
          <el-button v-if="loc==2"
              size="mini"
              type="primary"
              @click="insertInfo(scope.row)">更改信息</el-button>
          <el-button v-if="loc==2"
             size="mini"
             type="primary"
             @click="dialogSelectVisible = true;select_cno=scope.row.cno;">课程优良率</el-button>
          <el-button v-if="loc==2"
              size="mini"
              type="danger"
              @click="del(scope.row.cno)">删除课程</el-button>
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

  <el-dialog title="课程信息" :visible.sync="dialogFormVisible" width="30%" :before-close="handleClose">
    <el-form label-width="80px" size="small">
      <el-form-item label="课程代码">
        <el-input v-model="form.cno" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="课程名称">
        <el-input v-model="form.cname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="课程类型">
        <el-input v-model="form.ctype" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item v-if="loc==2" label="教师号">
        <el-input v-model="form.tno" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="校区">
        <el-input v-model="form.cpos" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学分">
        <el-input v-model="form.ccredit" autocomplete="off"></el-input>
      </el-form-item>
<!--      <el-form-item label="剩余人数">-->
<!--        <el-input v-model="form.cremain" autocomplete="off"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="容量">
        <el-input v-model="form.ccapacity" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="save">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
  </el-dialog>
    <el-dialog title="课程信息" :visible.sync="dialogChangeVisible" width="30%" :before-close="handleClose">
      <el-form label-width="80px" size="small">
        <el-form-item label="课程代码">
          <el-input v-model="form.cno" autocomplete="off" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="课程名称">
          <el-input v-model="form.cname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程类型">
          <el-input v-model="form.ctype" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师号">
          <el-input v-model="form.tno" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="校区">
          <el-input v-model="form.cpos" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="form.ccredit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="剩余人数">
          <el-input v-model="form.cremain" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="容量">
          <el-input v-model="form.ccapacity" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="update">确 定</el-button>
        <el-button @click="dialogChangeVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="课程信息" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;" >
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag action="http://localhost:9090/course/import" accept=".xls, .xlsx"
                     :on-success="handleFileUploadSuccess" :headers="header">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb，格式请参考新增</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="template()">模板下载</el-button>
      </div>
    </el-dialog>
    <el-dialog title="课程信息" :visible.sync="dialogSelectVisible" width="30%"
               :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <div id="main" style="width: 500px; height: 400px"></div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogSelectVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style scoped>

</style>