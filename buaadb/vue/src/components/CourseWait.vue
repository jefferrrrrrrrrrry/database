<script>
import request from "@/utils/request";
import * as XLSX from "xlsx";

export default {
  name: "CourseWait",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      if(this.loc==1){
        request.get("http://localhost:9090/course/findPend",{
          params:{
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          this.tableData=res.data.page;
        this.tableData.sort(function(a, b) {
          return a.cname.toLowerCase().localeCompare(b.cname.toLowerCase());
        });
          this.total=res.data.total;
        });
      }else if(this.loc==2){
        request.get("http://localhost:9090/course/managerfindPend",{
          params:{
            cno:this.s_cno,
            cname:this.s_cname,
            tname:this.s_tname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          this.tableData=res.data.page;
        this.tableData.sort(function(a, b) {
          return a.cname.toLowerCase().localeCompare(b.cname.toLowerCase());
        });
          this.total=res.data.total;
        });
      }
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname="";
      this.find();
    },
    find(){
      if(this.loc==1){
        request.get("http://localhost:9090/course/findPend",{
          params:{
            cno:this.s_cno,
            cname:this.s_cname,
            tname:this.s_tname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          this.tableData=res.data.page;
        this.tableData.sort(function(a, b) {
          return a.cname.toLowerCase().localeCompare(b.cname.toLowerCase());
        });
          this.total=res.data.total;
        });
      }else if(this.loc==2){
        request.get("http://localhost:9090/course/managerfindPend",{
          params:{
            cno:this.s_cno,
            cname:this.s_cname,
            tname:this.s_tname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          this.tableData=res.data.page;
        this.tableData.sort(function(a, b) {
          return a.cname.toLowerCase().localeCompare(b.cname.toLowerCase());
        });
          this.total=res.data.total;
        });
      }

    },
    del(id){
      request.delete("http://localhost:9090/course/"+id).then(res=>{
        if(res.data.status==="SUCCESS"){
          this.$message.success("退选成功")
          this.dialogVisible=false;
          this.load();
        }else{
          this.$message.error("退选失败")
        }
      })
    },
    accept(id){
      request.post("http://localhost:9090/course/approve",id).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("同意开课成功")
          this.load()
        }else{
          this.$message.error("同意开课失败")
        }
      })
    },
    refuse(id){
      request.post("http://localhost:9090/course/disapprove",id).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("拒绝开课成功")
          this.load()
        }else{
          this.$message.error("拒绝开课失败")
        }
      })
    },
    cancel(id){
      request.delete("http://localhost:9090/course/"+id,{
        params:{
          cno:id,
        }
      }).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("取消开课成功")
          this.dialogVisible=false;
          this.load();
        }else{
          this.$message.error("取消开课失败")
        }
      })
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      ////console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      //console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },exports(){
      const ws = XLSX.utils.json_to_sheet(this.tableData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
      if(this.loc==1){
        XLSX.writeFile(wb, '待审核课程.xlsx');
      }else if(this.loc==2){
        XLSX.writeFile(wb, '待审核课程.xlsx');
      }
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
      loc:0,
      page:1,
    }
  },
  created() {
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;

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
    this.load();
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/${id}" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==1"><a href='/${id}/courseOpen' >待审核课程</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==2"><a href='/${id}/courseOpen' >审核开课</a></el-breadcrumb-item>
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
      <el-button type="primary" @click="exports" >课程导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="cno" label="课程代码" width="100">
      </el-table-column>
      <el-table-column prop="cname" label="课程名称" width="150">
      </el-table-column>
      <el-table-column prop="ctype" label="课程类型" width="150">
      </el-table-column>
      <el-table-column prop="tname" label="开课教师" width="150">
      </el-table-column>
      <el-table-column prop="cpos" label="校区" width="150">
      </el-table-column>
      <el-table-column prop="ccredit" label="学分" width="120">
      </el-table-column>
      <el-table-column prop="cremain" label="剩余人数" width="80">
      </el-table-column>
      <el-table-column prop="ccapacity" label="容量" width="80">
      </el-table-column>
      <el-table-column label="操作" v-if="loc==1||loc==2">
        <template slot-scope="scope" >
          <el-button v-if="loc==2"
               size="mini"
               type="primary"
               @click="accept(scope.row.cno)">同意</el-button>
          <el-button v-if="loc==2"
               size="mini"
               type="danger"
               @click="refuse(scope.row.cno)">拒绝</el-button>
          <el-button v-if="loc==1"
              size="mini"
              type="danger"
              @click="cancel(scope.row.cno)">取消开课</el-button>
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
  </div>
</template>

<style scoped>

</style>