export default function authHeader() {
    let token = JSON.parse(localStorage.getItem('token'))
    if (token) {
        return {
            'accessJwt': token.accessJwt
        }
    } else {
        return {}
    }
}