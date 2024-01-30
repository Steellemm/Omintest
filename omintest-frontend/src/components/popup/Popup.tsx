import { FC, useContext } from 'react'
import Button from '../buttons/button/Button.tsx'
import { Context } from '../../main.tsx'
import styles from './Popup.module.scss'
import cn from 'classnames'
import svg from '../../assets/svg/sprite.svg'
import { Controller, SubmitHandler, useForm } from 'react-hook-form'
import { IPopupFormData } from '../../types/components/popup/popup-form-data'
import { useNavigate } from 'react-router-dom'
import { DRAW_ROUTE } from '../../utils/variables'
import InputField from '../inputs/input-field/InputField'

const Popup: FC = () => {
    const navigate = useNavigate()
    const { isShowPopup, setIsShowPopup, tests, setTests } = useContext(Context)


    const handlerClose = () => {
        setIsShowPopup(!isShowPopup)
    }

    const {
        control,
        handleSubmit,
        formState: { isValid }
    } = useForm<IPopupFormData>({
        mode: 'onSubmit',
        reValidateMode: 'onChange'
    })

    const onSubmit: SubmitHandler<IPopupFormData> = async (data: IPopupFormData) => {
        const id = String(new Date().getMilliseconds())
        setTests([...tests, { id: id, title: data.title, description: data.description }])
        navigate(DRAW_ROUTE + '/' + id)
        setIsShowPopup(false)
    }


    return (
        <div className={cn(styles.popup, isShowPopup && styles.popupActive)}>
            <button className={styles.close} onClick={handlerClose}>
                <svg className={styles.closeIcon}>
                    <use href={`${svg}#close`}/>
                </svg>
            </button>
            <div className={styles.inner}>
                <div className={styles.top}>
                    <h1 className={styles.title}>Создание теста</h1>
                </div>

                <form className={styles.body} onSubmit={handleSubmit(onSubmit)} noValidate={true}>
                    <Controller render={({ field: { onChange }, fieldState: { error } }) => {
                        return (
                            <InputField
                                placeholder="input"
                                title={'Название теста'}
                                onChange={onChange}
                                error={error}
                            />
                        )
                    }}
                    name={'title'}
                    control={control}
                    rules={{
                        required: {
                            value: true,
                            message: 'Название должно быть заполнено'
                        }
                    }}/>
                    <Controller render={({ field: { onChange }, fieldState: { error } }) => {
                        return (
                            <InputField
                                placeholder="input"
                                title={'Описание теста'}
                                onChange={onChange}
                                error={error}
                            />
                        )
                    }}
                    name={'description'}
                    control={control}
                    rules={{
                        required: {
                            value: true,
                            message: 'Описание должно быть заполнено'
                        }
                    }}/>
                    <div className={styles.bottom}>
                        <Button type={'over'} submit={true} filled={true} disabled={!isValid} text={'создать тест'}/>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Popup
