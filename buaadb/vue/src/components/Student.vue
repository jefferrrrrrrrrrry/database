<script>
import request from "@/utils/request";
import teacherCourseOpenView from "@/views/Teacher/TeacherCourseOpenView.vue";
import * as XLSX from "xlsx";
import * as echarts from "echarts";

export default {
  name: "Student",
  computed: {
    teacherCourseOpenView() {
      return teacherCourseOpenView
    }
  },
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      this.cno = this.$route.params.cno;
      request.get("http://localhost:9090/student/coursechosen",{
        params:{
          cno:this.cno,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },reset(){
      this.s_name="";
      this.s_permission="";
      this.load();
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      //console.log(pageSize);
      this.pageSize=pageSize;
      this.load();
    },handleCurrentChange(currentPage){
      //console.log(currentPage);
      this.currentPage=currentPage;
      this.load();
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },handleGrade(row) {
      this.dialogVisible = true;
      this.sel.sno = row.sno;
      this.sel.cno = this.cno;
    },save(){
      //this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = false
    },saveFile(){
      this.fileLoadVisible=false;
    },handleClose(){
      _ => {
        done();
      }
      this.dialogVisible=false;
    },pre(item){
        this.mood="学生";
        this.student.clno=item.clno;
        this.student.sage=item.sage;
        this.student.sgrade=item.sgrade;
        this.student.sname=item.sname;
        this.student.sno=item.sno;
        this.student.ssex=item.ssex;
    },exports(){
      request.get("http://localhost:9090/student/coursechosen",{
        params:{
          cno:this.cno,
          pageNum:1,
          pageSize:10000
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.writeFile(wb, '学生信息.xlsx');
        this.find();
      });
      
      //window.open("http://localhost:9090/course/export");
    },
    grade(){
      request.post("http://localhost:9090/course/recordgrade",this.sel).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("录入成功")
          this.load();
        }else{
          this.$message.error(res.msg)
        }
        this.dialogVisible=false
      })
    },
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
      cno:0,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      form:{},
      user:{},
      student:{
        sno:"",
        sname:"",
        sage:"",
        ssex:"",
        sgrade:"",
        spassword:"",
        clno:"",
      },
      search_mood:"0",
      select_sno:"",
      sel:{
        sno:"",
        cno:"",
        segrade:""
      },
      myChart:null,
      chartDom:null,
    }
  },
  created() {
    //console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.load();
  },
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/teacher" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item :to='{ path: "/teacher/courseopen" } ' >已开课程</el-breadcrumb-item>
      <el-breadcrumb-item>选课学生</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="tableData">
      <el-table-column prop="sno" label="学号" width="120">
      </el-table-column>
      <el-table-column prop="sname" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sage" label="年龄" width="120">
      </el-table-column>
      <el-table-column prop="ssex" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="sgrade" label="年级" width="120">
      </el-table-column>
      <el-table-column prop="scredit" label="总学分" width="120">
      </el-table-column>
      <el-table-column prop="clno" label="班级" width="120">
      </el-table-column>
      <el-table-column prop="scname" label="学院" width="120">
      </el-table-column>
      <el-table-column prop="score" label="成绩" width="120">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="primary"
              @click="handleGrade(scope.row)" >成绩录入</el-button>
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

    <el-dialog title="成绩信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
                  <el-form-item label="课程代码">
                    <el-input v-model="sel.cno" autocomplete="off" disabled="true"></el-input>
                  </el-form-item>
          <el-form-item label="学号">
            <el-input v-model="sel.sno" autocomplete="off" disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="分数">
            <el-input v-model="sel.segrade" autocomplete="off" placeholder="请输入0-100的整数"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="grade">确 定</el-button>
        <el-button @click="dialogVisible = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<style scoped>

</style>