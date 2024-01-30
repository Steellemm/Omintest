import { FC, FormEvent, useState } from 'react'
import styles from './AuthPage.module.scss'
import InputField from '../../components/inputs/input-field/InputField.tsx'
import Button from '../../components/buttons/button/Button.tsx'


const ConfirmPage: FC = () => {

    const [inputValue, setInputValue] = useState<string>('')

    const onSubmit = (e: FormEvent) => {
        e.preventDefault()
        console.log('🍁: ', inputValue)
    }

    const onChange = (e: string) => {
        setInputValue(e)
    }


    return (
        <div className={styles.main}>
            <div className={styles.auth}>
                <form className={styles.form} onSubmit={onSubmit} noValidate={true}>
                    <div className={styles.container}>
                        <div>
                            <h1 className={styles.title}>{'Регистрация'}</h1>
                        </div>
                        <div className={styles.fields}>
                            <InputField
                                placeholder="12345"
                                title={'Повтарите пароль'}
                                onChange={onChange}
                            />
                            <Button type={'over'} submit={true} filled={true} disabled={!inputValue}
                                text={'Зарегистрировать аккаунт'}/>
                        </div>
                        <div className={styles.bottom}></div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default ConfirmPage



