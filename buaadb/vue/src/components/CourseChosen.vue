<script>
import request from "@/utils/request";
import * as XLSX from "xlsx";

export default {
  name: "CourseChosen",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      console.log(this.loc);
      if(this.loc==0){
        request.get("http://localhost:9090/course/studentselect",{
          params:{
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res => {
          console.log(res);
          this.tableData = res.data.page;
          this.total = res.data.total;
        });
      }else if(this.loc==1){
        request.get("http://localhost:9090/course/findApprove",{
          params:{
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res => {
          console.log(res);
          this.tableData = res.data.page;
          this.total = res.data.total;
        });
      }
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname;
      this.find();
    },
    find(){
      if(this.loc==0){
        request.get("http://localhost:9090/course/studentselect",{
          params:{
            cno:this.s_cno,
            cname:this.s_cname,
            tname:this.s_tname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          console.log("teacher")
          console.log(res.data);
          this.tableData=res.data.page;
          this.total=res.data.total;
        });}
      else if(this.loc==1){
        request.get("http://localhost:9090/course/findApprove",{
          params:{
            cno:this.s_cno,
            cname:this.s_cname,
            tname:this.s_tname,
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res=>{
          console.log(res.data);
          this.tableData=res.data.page;
          this.total=res.data.total;
        });
       }
    },
    del(id){
      request.post("http://localhost:9090/course/withdraw",id).then(res=>{
        if(res.data.status==="SUCCESS"){
          this.$message.success("退选成功")
          this.dialogVisible=false;
          this.load();
        }else{
          this.$message.error("退选失败")
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
      console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },exports(){
        const ws = XLSX.utils.json_to_sheet(this.tableData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        if(this.loc==0){
          XLSX.writeFile(wb, '已选课程.xlsx');
        }else if(this.loc==1){
          XLSX.writeFile(wb, '已开课程.xlsx');
        }else{
          XLSX.writeFile(wb, '已选课程.xlsx');
        }
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:2,
      s_cname:"",
      s_cno:"",
      s_tname:"",
      dialogVisible: false,
      loc:0,
      page:1,
    }
  },
  created() {
    console.log(this.$route);
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
    <el-breadcrumb-item v-if="this.loc==0"><a href='/${id}/courseChosen' >已选课程</a></el-breadcrumb-item>
    <el-breadcrumb-item v-if="this.loc==1"><a href='/${id}/courseOpen' >已开课程</a></el-breadcrumb-item>
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
    <el-table-column prop="cno" label="课程代码" width="150">
    </el-table-column>
    <el-table-column prop="cname" label="课程名称" width="200">
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
    <el-table-column label="操作" v-if="this.loc==0">
      <template slot-scope="scope">
        <el-button
            size="mini"
            type="danger"
            @click="del(scope.row.cno)">退选</el-button>
      </template>
    </el-table-column>
    <el-table-column label="操作" v-if="this.loc==1">
      <template slot-scope="scope">
        <el-button
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