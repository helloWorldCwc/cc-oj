/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_QuestionInfoVO_ } from '../models/BaseResponse_Page_QuestionInfoVO_';
import type { BaseResponse_QuestionInfoVO_ } from '../models/BaseResponse_QuestionInfoVO_';
import type { DeleteRequest } from '../models/DeleteRequest';
import type { QuestionInfoAddRequest } from '../models/QuestionInfoAddRequest';
import type { QuestionInfoEditRequest } from '../models/QuestionInfoEditRequest';
import type { QuestionInfoQueryRequest } from '../models/QuestionInfoQueryRequest';
import type { QuestionInfoUpdateRequest } from '../models/QuestionInfoUpdateRequest';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class QuestionInfoInfoControllerService {

    /**
     * addQuestionInfo
     * @param questionAddRequest questionAddRequest
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addQuestionInfoUsingPost(
questionAddRequest: QuestionInfoAddRequest,
): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/add',
            body: questionAddRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * deleteQuestionInfo
     * @param deleteRequest deleteRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static deleteQuestionInfoUsingPost(
deleteRequest: DeleteRequest,
): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/delete',
            body: deleteRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * editQuestionInfo
     * @param questionEditRequest questionEditRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static editQuestionInfoUsingPost(
questionEditRequest: QuestionInfoEditRequest,
): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/edit',
            body: questionEditRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * getQuestionInfoVOById
     * @param id id
     * @returns BaseResponse_QuestionInfoVO_ OK
     * @throws ApiError
     */
    public static getQuestionInfoVoByIdUsingGet(
id?: number,
): CancelablePromise<BaseResponse_QuestionInfoVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/question/get/vo',
            query: {
                'id': id,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * listQuestionInfoVOByPage
     * @param questionQueryRequest questionQueryRequest
     * @returns BaseResponse_Page_QuestionInfoVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listQuestionInfoVoByPageUsingPost(
questionQueryRequest: QuestionInfoQueryRequest,
): CancelablePromise<BaseResponse_Page_QuestionInfoVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/list/page/vo',
            body: questionQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * listMyQuestionInfoVOByPage
     * @param questionQueryRequest questionQueryRequest
     * @returns BaseResponse_Page_QuestionInfoVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listMyQuestionInfoVoByPageUsingPost(
questionQueryRequest: QuestionInfoQueryRequest,
): CancelablePromise<BaseResponse_Page_QuestionInfoVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/my/list/page/vo',
            body: questionQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * updateQuestionInfo
     * @param questionUpdateRequest questionUpdateRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateQuestionInfoUsingPost(
questionUpdateRequest: QuestionInfoUpdateRequest,
): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question/update',
            body: questionUpdateRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
