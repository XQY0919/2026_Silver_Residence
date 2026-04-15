const { createApp } = Vue

createApp({
    data() {
        return {
            activeCard: 'login', // 当前激活的卡片：'login' 或 'register'
            isLoading: false, // 加载状态
            loginError: '', // 登录错误信息
            registerError: '', // 注册错误信息
            // 登录表单数据
            loginForm: {
                username: '',
                password: ''
            },
            // 注册表单数据
            registerForm: {
                username: '',
                password: '',
                email: '',
                phone: ''
            }
        }
    },
    methods: {
        // 切换卡片
        switchCard(card) {
            this.activeCard = card;
            // 清除错误信息
            this.loginError = '';
            this.registerError = '';
        },
        
        // 输入框聚焦处理
        handleFocus(e) {
            e.target.parentElement.classList.add('has-content');
        },
        
        // 输入框失焦处理
        handleBlur(e) {
            if (!e.target.value) {
                e.target.parentElement.classList.remove('has-content');
            }
        },
        
        // 登录功能
        async login() {
            // 表单验证
            if (!this.loginForm.username || !this.loginForm.password) {
                this.loginError = '请输入用户名和密码';
                return;
            }
            
            this.isLoading = true;
            this.loginError = '';
            
            try {
                const response = await fetch('http://localhost:8080/user/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.loginForm)
                });
                
                const data = await response.json();
                
                if (data.code === 1) {
                    // 登录成功，保存用户信息到本地存储
                    localStorage.setItem('user', JSON.stringify(data.data));
                    // 这里可以跳转到主页或其他页面
                    alert('登录成功！');
                } else {
                    // 登录失败
                    this.loginError = data.message || '登录失败';
                }
            } catch (error) {
                console.error('登录失败:', error);
                this.loginError = '网络错误，请稍后重试';
            } finally {
                this.isLoading = false;
            }
        },
        
        // 注册功能
        async register() {
            // 表单验证
            if (!this.registerForm.username || !this.registerForm.password || !this.registerForm.email || !this.registerForm.phone) {
                this.registerError = '请填写所有必填字段';
                return;
            }
            
            this.isLoading = true;
            this.registerError = '';
            
            try {
                const response = await fetch('http://localhost:8080/user/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.registerForm)
                });
                
                const data = await response.json();
                
                if (data.code === 1) {
                    // 注册成功，切换到登录卡片
                    this.switchCard('login');
                    // 显示成功信息
                    this.loginError = '注册成功，请登录';
                    // 3秒后清除成功信息
                    setTimeout(() => {
                        this.loginError = '';
                    }, 3000);
                } else {
                    // 注册失败
                    this.registerError = data.message || '注册失败';
                }
            } catch (error) {
                console.error('注册失败:', error);
                this.registerError = '网络错误，请稍后重试';
            } finally {
                this.isLoading = false;
            }
        }
    }
}).mount('#app')