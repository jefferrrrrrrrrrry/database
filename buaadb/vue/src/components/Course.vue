<script>
import request from "@/utils/request";
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
        //console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      var out_cno=id;
      request.post("http://localhost:9090/course/withdraw",out_cno).then(res=>{
        console.log(res)
        if(res.status==="SUCCESS"){
          this.$message.success("退选成功");
          this.load();
        }else{
          this.$message.error("退选失败");
        }
      })
    },
    choose(id){
      request.post("http://localhost:9090/course/selectCourse",id).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("选课成功");
          this.load();
        }else{
          this.$message.error("选课失败，你已选")
        }
      })
    },save(){
      //this.form = JSON.parse(JSON.stringify(row))
      this.form.status="1";
      if(this.loc==1){
        request.post("http://localhost:9090/course/add",this.form).then(res=>{
          console.log(res)
          if(res.status==="SUCCESS"){
            this.$message.success("申请已递交教务审核");
            this.load();
          }else{
            this.$message.error("申请失败，请核对信息")
          }
        })
      }else{
        request.post("http://localhost:9090/course/manageradd",this.form).then(res=>{
          console.log(res)
          if(res.status==="SUCCESS"){
            this.$message.success("申请已递交教务审核");
            this.load();
          }else{
            this.$message.error("申请失败，请核对信息")
          }
        })
      }
      //this.dialogFormVisible = false
    }, formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    }, handleSizeChange(pageSize){
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
    },handleSuccess(response, file, fileList){
      if(file==null){
        console.log(file)
        this.$message.error("添加失败，请核对文件格式")
      } else{
        request.post("http://localhost:9090/course/import",file).then(res=>{
          console.log(res)
          if(res.status==="SUCCESS"){
            this.$message.success("添加成功");
            this.load();
          }else{
            this.$message.error("添加失败，请核对文件格式")
          }
        })
        this.fileLoadVisible=false;
      }
    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogFormVisible = false;
    },exports(mood){

        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        if(mood==0)XLSX.writeFile(wb, '全部课程.xlsx');
        else XLSX.writeFile(wb, '模板.xlsx');
      //window.open("http://localhost:9090/course/export");
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
      form:{},
      file:null,

    }
  },
  created() {
    //console.log(this.$route);
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
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/${id}" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==0"><a href='/${id}/course' >选课</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==1"><a href='/${id}/course' >全部课程</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==2"><a href='/${id}/course' >课程管理</a></el-breadcrumb-item>
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
      <el-button type="primary" @click="exports(0)" >课程导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="cno" label="课程代码" width="100">
      </el-table-column>
      <el-table-column prop="cname" label="课程名称" width="150">
      </el-table-column>
      <el-table-column prop="ctype" label="课程类型" width="150">
      </el-table-column>
      <el-table-column prop="tname" label="开课老师" width="150">
      </el-table-column>
      <el-table-column prop="cpos" label="校区" width="150">
      </el-table-column>
      <el-table-column prop="ccredit" label="学分" width="120">
      </el-table-column>
      <el-table-column prop="cremain" label="剩余人数" width="80">
      </el-table-column>
      <el-table-column prop="ccapacity" label="容量" width="80">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button v-if="loc==0"
              size="mini"
              type="danger"
              @click="choose(scope.row.cno)">选课</el-button>
          <el-button v-if="loc==2"
              size="mini"
              type="primary"
              @click="del(scope.row.cno)">更改信息</el-button>
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
      <el-button type="primary" @click="save">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
  </el-dialog>
    <el-dialog title="课程信息" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" accept=".xls, .xlsx"
                     :on-success="handleSuccess">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb，格式请参考新增</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="exports(1)">模板下载</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style scoped>

</style>