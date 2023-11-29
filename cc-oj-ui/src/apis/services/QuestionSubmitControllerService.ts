/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_int_ } from '../models/BaseResponse_int_';
import type { BaseResponse_Page_QuestionSubmitVO_ } from '../models/BaseResponse_Page_QuestionSubmitVO_';
import type { QuestionSubmitAddRequest } from '../models/QuestionSubmitAddRequest';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class QuestionSubmitControllerService {

    /**
     * pageList
     * @param code 
     * @param current 
     * @param language 
     * @param pageSize 
     * @param questionId 
     * @param sortField 
     * @param sortOrder 
     * @param status 
     * @param submitJudgeInfoMemory 
     * @param submitJudgeInfoMessage 
     * @param submitJudgeInfoTime 
     * @param userId 
     * @returns BaseResponse_Page_QuestionSubmitVO_ OK
     * @throws ApiError
     */
    public static pageListUsingGet(
code?: string,
current?: number,
language?: string,
pageSize?: number,
questionId?: number,
sortField?: string,
sortOrder?: string,
status?: number,
submitJudgeInfoMemory?: number,
submitJudgeInfoMessage?: string,
submitJudgeInfoTime?: number,
userId?: number,
): CancelablePromise<BaseResponse_Page_QuestionSubmitVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/question_submit/page/vo/list',
            query: {
                'code': code,
                'current': current,
                'language': language,
                'pageSize': pageSize,
                'questionId': questionId,
                'sortField': sortField,
                'sortOrder': sortOrder,
                'status': status,
                'submitJudgeInfo.memory': submitJudgeInfoMemory,
                'submitJudgeInfo.message': submitJudgeInfoMessage,
                'submitJudgeInfo.time': submitJudgeInfoTime,
                'userId': userId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * doQuestionSubmit
     * @param questionSubmitAddRequest questionSubmitAddRequest
     * @returns BaseResponse_int_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static doQuestionSubmitUsingPost(
questionSubmitAddRequest: QuestionSubmitAddRequest,
): CancelablePromise<BaseResponse_int_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/question_submit/submit',
            body: questionSubmitAddRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
