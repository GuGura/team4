<template>
  <div class="inputBox">
    <div class="text">
      <div id="idInfo">{{ props.subject }}</div>
      <div :style="props.noticeColor">{{ props.notice }}</div>
    </div>
    <input class="textBox" :type="props.boxType" :name="props.boxName" @input="handleModelValue"
           :pattern="props.pattern" :autocomplete="props.autocomplete" required>
  </div>
</template>

<script setup>
import {defineProps, defineEmits} from 'vue'

const props = defineProps({
  boxType: String,
  boxName: String,
  subject: String,
  modelValue: String,
  pattern: String,
  notice: {
    type: String,
    default: '*'
  },
  noticeColor: String,
  autocomplete: String
});

const emit = defineEmits(['update:modelValue'])

const handleModelValue = (event) => {
  const inputValue = event.target.value;
  emit('update:modelValue', inputValue)
}
</script>
<style scoped>
.inputBox {
  /* Auto layout */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0;
  gap: 8px;

  width: 100%;
}

.text {
  display: flex;
  gap: 8px;
}

.textBox {
  width: 95%;
  height: 40px;
  background: #1E1F22;
  border-radius: 3px;
  color: white;
  outline: none;
  border: none;
  padding: 0 10px;
  box-sizing: border-box;
}

.text > div:nth-of-type(1), .text > div:nth-of-type(2) {
  font-family: 'Noto Sans';
  font-weight: 700;
  font-style: normal;
  line-height: 16px;
  display: flex;
  align-items: center;
  text-transform: uppercase;
}

.text > div:nth-of-type(1) {
  font-size: 12px;
  /* identical to box height, or 145% */
  color: #B5BAC1;
}

.text > div:nth-of-type(2) {
  font-size: 10px;
  /* identical to box height, or 160% */
  color: #F23F42;
}
</style>