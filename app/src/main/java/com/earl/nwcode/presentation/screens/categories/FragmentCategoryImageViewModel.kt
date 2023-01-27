package com.earl.nwcode.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.nwcode.domain.Interactor
import com.earl.nwcode.domain.RequestResult
import com.earl.nwcode.domain.mappers.ImageDomainToUiMapper
import com.earl.nwcode.presentation.models.ImageUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FragmentCategoryImageViewModel @Inject constructor(
    private val interactor: Interactor,
    private val imageDomainToUiMapper: ImageDomainToUiMapper<ImageUi>
): ViewModel() {

    private val images: MutableStateFlow<List<ImageUi?>> = MutableStateFlow(emptyList())
    val _images = images.asStateFlow()
    private val requestError: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val _requestError = requestError.asStateFlow()

    fun fetchPicturesForCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val requestResult = interactor.fetchImageForCategory(category)
            withContext(Dispatchers.Main) {
                when(requestResult) {
                    is RequestResult.Success -> images.value = requestResult.data
                        .map { it.mapToUi(imageDomainToUiMapper) }
                    is RequestResult.Fail -> requestError.value = requestResult.error
                }
            }
        }
    }
}