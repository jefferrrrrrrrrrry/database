<script>
import request from "@/utils/request";
import * as XLSX from "xlsx";
import * as echarts from "echarts";

export default {
  name: "CourseChosen",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      //console.log(this.loc);
      if(this.loc==0){
        request.get("http://localhost:9090/course/studentselect",{
          params:{
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res => {
          //console.log(res);
          this.tableData = res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
          this.total = res.data.total;

        });
      }else if(this.loc==1){
        request.get("http://localhost:9090/course/findApprove",{
          params:{
            pageNum:this.currentPage,
            pageSize:this.pageSize
          }
        }).then(res => {
          //console.log(res);
          this.tableData = res.data.page;
          this.total = res.data.total;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
        });
      }
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname="";
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
          //console.log("teacher")
          //console.log(res.data);
          this.tableData=res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
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
          //console.log(res.data);
          this.tableData=res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
          this.total=res.data.total;
        });
       }
    },
    del(id){
      request.post("http://localhost:9090/course/withdraw",id).then(res=>{
        console.log(res)
        if(res.status==="SUCCESS"){
          this.$message.success("退选成功")
          this.load();
        }else{
          this.$message.error(res.msg)
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
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    grade(){
      if(this.sel.segrade>100000){
        this.sel.segrade=100000;
      }
      if(this.sel.segrade<-100000){
        this.sel.segrade=-100000;
      }
      request.post("http://localhost:9090/course/recordgrade",this.sel).then(res=>{
        if(res.status==="SUCCESS"){
          this.$message.success("录入成功")
          this.load();
        }else{
          this.$message.error(res.msg)
        }
        this.dialogVisible = false;
      })

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
    },handleGrade(row){
      this.dialogVisible = true;
      this.sel.cno = row.cno;
    },handleClose(){
      _ => {
        done();
      }
      this.dialogVisible = false;
      this.dialogSelectVisible=false;
    },exports(){
      if(this.loc==0){
        request.get("http://localhost:9090/course/studentselect",{
          params:{
            pageNum:1,
            pageSize:10000
          }
        }).then(res => {
          //console.log(res);
          this.tableData = res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
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
          this.find()

        });
      }else if(this.loc==1){
        request.get("http://localhost:9090/course/findApprove",{
          params:{
            pageNum:1,
            pageSize:10000
          }
        }).then(res => {
          //console.log(res);
          this.tableData = res.data.page;
          for (let i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].score === null) {
              this.tableData[i].score = '暂无';
            }
          }
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
          this.find()
        });
      }
        
    }, searchStudent(row) {
      // this.$router.push("/teacher/search")
      this.$router.push({
        //path: "/teacher/search",
        name: 'teacherCourseSelect',
        params: {
          cno: row.cno
        }
      })
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
      sel:{},
      option:"",
      myChart:null,
      chartDom:null,
      dialogSelectVisible:false,
      select_cno:"",
    }
  },
  created() {
    //console.log(this.$route);
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
    <el-breadcrumb-item v-if="this.loc==0"><a href='/student/courseChosen' >已选课程</a></el-breadcrumb-item>
    <el-breadcrumb-item v-if="this.loc==1"><a href='/teacher/courseOpen' >已开课程</a></el-breadcrumb-item>
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
    <el-table-column prop="cname" label="课程名称" width="130">
    </el-table-column>
    <el-table-column prop="ctype" label="课程类型" width="100">
    </el-table-column>
    <el-table-column prop="tname" label="开课教师" width="100">
    </el-table-column>
    <el-table-column prop="cpos" label="校区" width="120">
    </el-table-column>
    <el-table-column prop="ccredit" label="学分" width="60">
    </el-table-column>
    <el-table-column prop="score" label="成绩" width="80" v-if="this.loc==0">
    </el-table-column>
    <el-table-column prop="cremain" label="剩余人数" width="80">
    </el-table-column>
    <el-table-column prop="ccapacity" label="容量" width="90">
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
            type="primary"
            @click="searchStudent(scope.row)">查看学生</el-button>
        <el-button
            size="mini"
            type="primary"
            @click="handleGrade(scope.row)">成绩录入</el-button>
        <el-button
            size="mini"
            type="primary"
            @click="dialogSelectVisible = true;select_cno=scope.row.cno;">查看优良率</el-button>
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
  <el-dialog title="成绩信息" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
    <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
      <el-form label-width="80px" size="small" >
        <el-form-item label="课程代码">
          <el-input v-model="sel.cno" autocomplete="off" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="sel.sno" autocomplete="off" placeholder="请输入学生学号"></el-input>
        </el-form-item>
        <el-form-item label="分数">
          <el-input v-model="sel.segrade" autocomplete="off" placeholder="请输入0-100的整数" type="number" max=200></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="grade">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
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