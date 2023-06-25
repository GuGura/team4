export default {
    getNumberFormatted(val){
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
}

// 숫자 자리 수에 따라 ,추가해주는 함수