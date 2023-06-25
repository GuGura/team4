export default function refreshHeader() {
    let token = JSON.parse(localStorage.getItem('token'))
    if (token) {
        return {
            'refreshJwt': token.refreshJwt
        }
    } else {
        return {}
    }
}