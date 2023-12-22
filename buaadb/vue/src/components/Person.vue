<script>
import request from "@/utils/request";

export default {
  name: "Person",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/course/").then(res=>{
        console.log(res.data);
        this.tableData=res.data;
        this.total=res.length;
      });
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname;
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
        console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      request.delete("http://localhost:9090/course/"+id).then(res=>{
        if(res){
          this.$message.success("退选成功")
          this.dialogVisible=false;
          this.load();
        }else{
          this.$message.error("退选失败")
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
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },
    handleEdit(row) {
      //this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
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
      this.dialogFormVisible = false;
    },exports(){
      window.open("http://localhost:9090/course/export");
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
      form:{},
      user:{
        id:"",
        name:"",
        pwd:"",
      },
    }
  },
  created() {
    console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.find();
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/${id}" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href='/${id}/manageall' >管理人员</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-input style="flex:1;width:200px"  placeholder="请输入教师名" suffix-icon="el-icon-search" v-model="s_cname"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入学生名" suffix-icon="el-icon-search" v-model="s_cno"
                clearable></el-input>
      <el-input style="flex:1 ;width:200px"  placeholder="请输入管理者名" suffix-icon="el-icon-search" v-model="s_tname"
                clearable></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" >新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" >文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports" >信息导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="cno" label="身份" width="300">
      </el-table-column>
      <el-table-column prop="cname" label="账号" width="300">
      </el-table-column>
      <el-table-column prop="cpos" label="密码" width="300">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button
                     size="mini"
                     type="primary"
                     @click="del(scope.row.cno)">更改信息</el-button>
          <el-button
                     size="mini"
                     type="danger"
                     @click="del(scope.row.cno)">删除</el-button>
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
          <el-select v-model="user.id" placeholder="请选择身份">
            <el-option label="管理员" value="manager"></el-option>
            <el-option label="老师" value="teacher"></el-option>
            <el-option label="学生" value="student"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号">
          <el-input v-model="user.name" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.name" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="user.name" placeholder="请再次输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>

</template>

<style scoped>

</style>