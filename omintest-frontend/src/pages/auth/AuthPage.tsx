import { FC } from 'react'
import styles from './AuthPage.module.scss'
import { useLocation, useNavigate } from 'react-router-dom'
import { CONFIRM_ROUTE, LOGIN_ROUTE, REGISTRATION_ROUTE } from '../../utils/variables.ts'
import ButtonText from '../../components/buttons/button-text/ButtonText.tsx'
import { SubmitHandler, useForm } from 'react-hook-form'
import { IFormData } from '../../types/pages/auth-types.ts'
import Button from '../../components/buttons/button/Button.tsx'
import EmailFormController from '../../hoc/hook-form-controller/EmailFormController.tsx'
import PasswordFormController from '../../hoc/hook-form-controller/PasswordFormController.tsx'
import CheckboxFormController from '../../hoc/hook-form-controller/CheckboxFormController.tsx'


const AuthPage: FC = () => {
    const location = useLocation()
    const isLoginRoute = location.pathname === LOGIN_ROUTE
    const navigate = useNavigate()

    const {
        control,
        handleSubmit,
        formState: { isValid }
    } = useForm<IFormData>({
        mode: 'onSubmit',
        reValidateMode: 'onChange'
    })

    const onSubmit: SubmitHandler<IFormData> = async (data: IFormData) => {
        if (isLoginRoute) {
            console.log('[31] 🥕: LOGIN ', data)
        } else {
            console.log('[33] 🐬: REGISTRATION', data)
            navigate(CONFIRM_ROUTE)
        }
    }


    const handlerForgot = () => {
        console.log('[17] 🌻: Вспоминай скорее')
    }


    return (
        <div className={styles.main}>
            <div className={styles.auth}>
                <form className={styles.form} onSubmit={handleSubmit(onSubmit)} noValidate={true}>
                    <div className={styles.container}>
                        <div>
                            <h1 className={styles.title}>{isLoginRoute ? 'Авторизация' : 'Регистрация'}</h1>
                        </div>
                        <div className={styles.fields}>
                            <EmailFormController control={control} name={'email'}/>
                            <PasswordFormController control={control} name={'password'}/>

                            {!isLoginRoute && <PasswordFormController control={control} name={'password2'}/>}
                            {!isLoginRoute && <CheckboxFormController control={control} name={'agree'}/>}
                            {isLoginRoute && <ButtonText text={'Забыли пароль?'} onClick={handlerForgot}/>}
                            <Button type={'over'} submit={true} filled={true} disabled={!isValid}
                                text={isLoginRoute ? 'Войти' : 'Регистрация'}/>
                            <AuthChangeLink isLogin={isLoginRoute}/>
                        </div>
                        <div className={styles.bottom}></div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default AuthPage


const AuthChangeLink: FC<{ isLogin: boolean }> = ({ isLogin }) => {
    const navigate = useNavigate()
    const onClick = () => {
        isLogin ? navigate(REGISTRATION_ROUTE) : navigate(LOGIN_ROUTE)
    }

    return (
        <div className='text-center'>
            <ButtonText primary={true} text={isLogin ? 'Или создайте аккаунт' : 'Или войдите в аккаунт'}
                onClick={onClick}/>
        </div>
    )
}
