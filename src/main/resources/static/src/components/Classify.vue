<template>
  <div class="classify">
    <ul>
      <li class="li_acitve">综合</li>
      <li>编号</li>
      <li>名称</li>
      <li>创建时间</li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.classify ::after {
  clear: both;
  content: "";
  display: block;
  width: 0;
  height: 0;
  visibility: hidden;
}
.classify ul li {
  list-style: none;
  float: left;
  margin-left: 30px;
  color: #888;
}
.classify ul li :hover{
  cursor: pointer;
}
.li_acitve {
  color: #000 !important;
  border-bottom: 4px #dda37a solid;
}
</style>
