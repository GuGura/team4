<template>
  <div>
    <button>{{ props.text }}</button>
  </div>
</template>

<script setup>
// eslint-disable-next-line no-undef
const props = defineProps({
  text: String
})
</script>

<style scoped>
div {
  /* Auto layout */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  cursor: pointer;

  width: 97%;
  height: 46px;

  background: #5865F2;
  border-radius: 3px;
  box-sizing: border-box;
}

div:hover {
  background: #4752C4;
}

div:active {
  background: #3C45A6;
}

button {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 24px;
  display: flex;
  flex: 1;
  align-items: center;
  text-align: center;
  justify-content: center;
  color: #fff;
  background: none;
  height: 100%;
  border: none;
}
</style>