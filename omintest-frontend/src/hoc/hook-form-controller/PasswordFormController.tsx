import { FC } from 'react'
import { Controller } from 'react-hook-form'
import { IAuthFormControlerPropsTypes } from '../../types/pages/auth-form-controler-props-types.ts'
import InputFieldPass from '../../components/inputs/input-field-password/InputFieldPass.tsx'


const EmailFormController: FC<IAuthFormControlerPropsTypes> = ({ control, name }) => {
    return (
        <Controller render={({ field: { onChange }, fieldState: { error } }) => {
            return (
                <InputFieldPass
                    placeholder="********"
                    title={'Пароль'}
                    onChange={onChange}
                    error={error}
                />
            )
        }}
        name={name}
        control={control}
        rules={{
            required: {
                value: true,
                message: 'Пароль не может быть пустым'
            },
            minLength: {
                value: 8,
                message: 'Пароль джолжен быть не менее 8 символов'
            }
        }}/>
    )
}

export default EmailFormController
