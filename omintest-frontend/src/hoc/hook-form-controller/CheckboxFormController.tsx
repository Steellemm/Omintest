import { FC } from 'react'
import { Controller } from 'react-hook-form'
import { IAuthFormControlerPropsTypes } from '../../types/pages/auth-form-controler-props-types.ts'
import Checkbox from '../../components/checkboxes/checkbox/Checkbox.tsx'


const EmailFormController: FC<IAuthFormControlerPropsTypes> = ({ control, name }) => {
    return (
        <Controller render={({ field: { onChange } }) => {
            return (
                <Checkbox
                    text={'Для регистрации подтвердите согласие на обработку персональных данных'}
                    onChange={onChange}
                />
            )
        }}
        name={name}
        control={control}
        rules={{
            required: {
                value: true,
                message: 'Пароль не может быть пустым'
            }
        }}/>
    )
}

export default EmailFormController
